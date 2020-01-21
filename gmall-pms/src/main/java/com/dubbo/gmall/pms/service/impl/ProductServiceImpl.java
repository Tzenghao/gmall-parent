package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.*;
import com.dubbo.gmall.pms.mapper.*;
import com.dubbo.gmall.pms.service.ProductService;
import com.dubbo.gmall.vo.PageInfoVo;
import com.dubbo.gmall.vo.product.PmsProductParam;
import com.dubbo.gmall.vo.product.PmsProductQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Component  //容器中的对象默认是单例。
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private ProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private ProductLadderMapper productLadderMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    //当前线程共享同样的数据
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    //等同于线程共享(ThreadLocal的原理)
    private Map<Thread,Long> map = new HashMap<>();

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam parm) {
        Long branId = parm.getBrandId();
        String keyword = parm.getKeyword();
        Long productCategoryId = parm.getProductCategoryId();
        String productSn = parm.getProductSn();
        Integer publishStatus = parm.getPublishStatus();
        Integer verifyStatus = parm.getVerifyStatus();
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>().eq(!StringUtils.isEmpty(branId), "brand_id", branId)
                .like(!StringUtils.isEmpty(keyword), "name", keyword)
                .eq(!StringUtils.isEmpty(productCategoryId), "product_category_id", productCategoryId)
                .like(!StringUtils.isEmpty(productSn), "product_sn", productSn)
                .eq(!StringUtils.isEmpty(publishStatus), "publish_status", publishStatus)
                .eq(!StringUtils.isEmpty(verifyStatus), "verify_status", verifyStatus);
        IPage<Product> page = productMapper.selectPage(new Page<Product>(parm.getPageNum(), parm.getPageSize()), wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(page.getTotal(),page.getPages(),parm.getPageSize(),page.getRecords(),page.getCurrent());
        return pageInfoVo;
    }

    /**
     * 保存商品
     * @param productParam
     *
     * 考虑事务
     * 1）哪些东西是一定要回滚的，哪些东西即使出错了是不必要回滚的
     *      商品的核心信息（基本数据、sku）保存的时候，不要受到无关信息影响。
     *      无关信息出问题，核心信息也不用回滚
     *  2）事务的传播行为:propagation：当前方法的事务【是否和别人公用一个事务】如何传播下去（里面的方法如果用事务，是否和他公用一个事务）
     *     REQUIRED（必须的）：
     *          如果以前有事务，就和之前的事务公用一个事务，没有就创建一个事务
     *     REQUIRES_NEW（总是用新的事务）：
     *          创建一个新的事务，如果以前有事务，暂停前面的事务
     *     SUPPORTS（支持）：
     *          之前有事务，就以事务的方式运行，没有事务也可以
     *     MANDATORY（强制）：
     *          一定要有事务，如果没事务就报错
     *     NOT_SUPPORTED（不支持）：
     *          不支持在事务内运行，如果已经有事务了，就挂起当前存在的事务
     *     NEVER（从不使用）：
     *          不支持在事务内运行，如果已经有事务了，抛异常
     *     NESTED（）：
     *          开启一个子事务（MySQL不支持），需要支持还原点功能的数据库
     *
     *     总结：
     *          传播行为过程中，只要REQUIRES_NEW被执行过就一定成功，不管后面出不出问题，异常机制还是一样的，出现异常代码后不执行。
     *          REQUIRED只要感觉到异常就一定回滚。和外事务是什么传播行为无关。
     *          传播行为总是来定义，当一个事务存在的时候，他内部事务该怎么执行。
     *
     *     事务Spring中是怎么做的
     *     TransactionManager
     *     AOP做的
     *     CGLIB动态代理实现的
     *     这里如果直接调本类方法，相当于直接在内存中进行，所以只会有外面一个事务生效(自己类调用自己类里面的方法，就是一个复制粘贴)
     *     对象.方法()才能加上事务（对象调用方法时，使用的是代理对象来对方法进行调用的）。
     *
     *     事务的问题：
     *          Service自己调用自己的方法，无法加上真正的自己内部调整的各个事务
     *          解决：
     *              1）通过 对象.方法()解决(通过代理对象来对方法进行调用)
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveProduct(PmsProductParam productParam) {
        ProductServiceImpl currentProxy = (ProductServiceImpl) AopContext.currentProxy();
        //1)pms_product:保存商品基本信息
        currentProxy.saveBaseInfo(productParam);
        //5)pms_sku_stock:库存表
        currentProxy.saveSkuStock(productParam);
        /**
         * 以下都可以try-catch，使他们之间互不影响，即使代码错误只回滚自己的方法
         * 下面的方法也可以继续往下执行
         */
        //2)pms_product_attribute_value:保存这个商品对应所有属性的值
        currentProxy.saveProductAttributeValue(productParam);
        //3)pms_product_full_reduction:保存商品的满减信息
        currentProxy.saveFullReduction(productParam);
        //4)pms_product_ladder:阶梯价格表
        currentProxy.saveProductLadder(productParam);
        //以上的写法只是相当于一个saveProduct的事务（因为没用通过代理对象调用），所设置的隔离级别都会没有起作用

    }

    /**
     * pms_product:保存商品基本信息
     * @param productParam
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveBaseInfo(PmsProductParam productParam){
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        productMapper.insert(product);
        //mybatis-plus能自动获取到刚才这个数据的自增id
        log.info("刚才商品的id:{}",product.getId());
        threadLocal.set(product.getId());
        //ThreadLocal的原理
        //map.put(Thread.currentThread(),product.getId());
        log.info("当前线程...{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }

    /**
     * pms_sku_stock:保存库存表
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSkuStock(PmsProductParam productParam) {
        List<SkuStock> skuStockList = productParam.getSkuStockList();
        for (int i = 1; i <= skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i-1);
            if (StringUtils.isEmpty(skuStock.getSkuCode())){
                //skuCode必须有
                //生成规则:商品id_sku自增id
                skuStock.setSkuCode(threadLocal.get()+"_"+i);
            }
            skuStock.setProductId(threadLocal.get());
            skuStockMapper.insert(skuStock);
        }
        log.info("当前线程...{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }

    /**
     * pms_product_ladder:保存阶梯价格表
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductLadder(PmsProductParam productParam) {
        List<ProductLadder> productLadderList = productParam.getProductLadderList();
        productLadderList.forEach(productLadder -> {
            productLadder.setProductId(threadLocal.get());
            productLadderMapper.insert(productLadder);
        });
        log.info("当前线程...{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }

    /**
     * pms_product_full_reduction:保存商品的满减信息
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFullReduction(PmsProductParam productParam) {
        List<ProductFullReduction> productFullReductionList = productParam.getProductFullReductionList();
        productFullReductionList.forEach(reduction->{
            reduction.setProductId(threadLocal.get());
            productFullReductionMapper.insert(reduction);
        });
        log.info("当前线程...{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }



    /**
     * pms_product_attribute_value:保存这个商品对应所有属性的值
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductAttributeValue(PmsProductParam productParam){
        List<ProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        productAttributeValueList.forEach(item->{
            item.setProductId(threadLocal.get());
        });
        log.info("当前线程...{}-->{}",Thread.currentThread().getId(),Thread.currentThread().getName());
    }
}
