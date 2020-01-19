package com.dubbo.gmall.pms.mapper;

import com.dubbo.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dubbo.gmall.vo.product.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listCatelogWithChildre(Integer i);
}
