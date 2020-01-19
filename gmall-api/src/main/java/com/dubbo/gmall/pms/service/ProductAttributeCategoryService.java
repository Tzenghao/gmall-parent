package com.dubbo.gmall.pms.service;

import com.dubbo.gmall.pms.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dubbo.gmall.vo.PageInfoVo;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {
    /**
     * 分页查询所有属性的分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfoVo productAttributeCategoryPageInfo(Integer pageSize, Integer pageNum);
}
