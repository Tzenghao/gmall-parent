package com.dubbo.gmall.pms.service;

import com.dubbo.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dubbo.gmall.vo.PageInfoVo;
import com.dubbo.gmall.vo.product.PmsProductParam;
import com.dubbo.gmall.vo.product.PmsProductQueryParam;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface ProductService extends IService<Product> {
    /**
     * 根据复杂查询条件返回分页数据
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    /**
     * 保存商品
     * @param productParam
     */
    void saveProduct(PmsProductParam productParam);
}
