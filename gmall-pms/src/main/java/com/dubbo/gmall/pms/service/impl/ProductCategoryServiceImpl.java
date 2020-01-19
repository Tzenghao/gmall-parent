package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.constant.SysCacheConstant;
import com.dubbo.gmall.pms.entity.ProductCategory;
import com.dubbo.gmall.pms.mapper.ProductCategoryMapper;
import com.dubbo.gmall.pms.service.ProductCategoryService;
import com.dubbo.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
@Slf4j
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
   /**
     * 分布式缓存使用redis来做
     * @param i
     * @return
     */
    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChildre(Integer i) {
        List<PmsProductCategoryWithChildrenItem> items = (List<PmsProductCategoryWithChildrenItem>) redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        if (items==null){
            items = productCategoryMapper.listCatelogWithChildre(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY,items);
        }
        return items;
    }
}
