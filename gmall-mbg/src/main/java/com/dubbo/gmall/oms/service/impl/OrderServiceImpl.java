package com.dubbo.gmall.oms.service.impl;

import com.dubbo.gmall.oms.entity.Order;
import com.dubbo.gmall.oms.mapper.OrderMapper;
import com.dubbo.gmall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
