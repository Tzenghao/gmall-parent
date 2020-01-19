package com.dubbo.gmall.pms.service;

import com.dubbo.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dubbo.gmall.vo.product.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listCatelogWithChildre(Integer i);
}
