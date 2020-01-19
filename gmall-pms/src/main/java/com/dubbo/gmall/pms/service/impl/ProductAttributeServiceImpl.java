package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.ProductAttribute;
import com.dubbo.gmall.pms.mapper.ProductAttributeMapper;
import com.dubbo.gmall.pms.service.ProductAttributeService;
import com.dubbo.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Autowired
    private ProductAttributeMapper productAttributeMapper;
    @Override
    public PageInfoVo getCategoryAttribute(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        QueryWrapper<ProductAttribute> wrapper = new QueryWrapper<ProductAttribute>()
                .eq("product_attribute_category_id", cid)
                .eq("type", type);
        IPage<ProductAttribute> page = productAttributeMapper.selectPage(new Page<ProductAttribute>(pageNum,pageSize),wrapper);
        return PageInfoVo.getVo(page,pageSize.longValue());
    }
}
