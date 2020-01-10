package com.dubbo.gmall.pms.service.impl;

import com.dubbo.gmall.pms.entity.ProductLadder;
import com.dubbo.gmall.pms.mapper.ProductLadderMapper;
import com.dubbo.gmall.pms.service.ProductLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2020-01-09
 */
@Service
public class ProductLadderServiceImpl extends ServiceImpl<ProductLadderMapper, ProductLadder> implements ProductLadderService {

}
