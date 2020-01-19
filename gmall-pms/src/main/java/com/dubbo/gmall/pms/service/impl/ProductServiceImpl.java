package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.Product;
import com.dubbo.gmall.pms.mapper.ProductMapper;
import com.dubbo.gmall.pms.service.ProductService;
import com.dubbo.gmall.vo.PageInfoVo;
import com.dubbo.gmall.vo.product.PmsProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Component
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

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
}
