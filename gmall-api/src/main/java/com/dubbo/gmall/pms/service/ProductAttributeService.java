package com.dubbo.gmall.pms.service;

import com.dubbo.gmall.pms.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dubbo.gmall.vo.PageInfoVo;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface ProductAttributeService extends IService<ProductAttribute> {
    /**
     * 查询某个属性分类下得所有属性和参数
     * @param cid
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfoVo getCategoryAttribute(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
