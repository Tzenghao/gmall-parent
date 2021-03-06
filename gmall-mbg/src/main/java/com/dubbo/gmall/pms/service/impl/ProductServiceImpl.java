package com.dubbo.gmall.pms.service.impl;

import com.dubbo.gmall.pms.entity.Product;
import com.dubbo.gmall.pms.mapper.ProductMapper;
import com.dubbo.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
