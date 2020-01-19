package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.ProductAttributeCategory;
import com.dubbo.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.dubbo.gmall.pms.service.ProductAttributeCategoryService;
import com.dubbo.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {
    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public PageInfoVo productAttributeCategoryPageInfo(Integer pageSize, Integer pageNum) {
        IPage<ProductAttributeCategory> page = productAttributeCategoryMapper.selectPage(new Page<ProductAttributeCategory>(pageNum, pageSize), null);
        //返回分页数据对象
        return PageInfoVo.getVo(page,pageSize.longValue());
    }
}
