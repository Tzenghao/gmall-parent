package com.dubbo.gmall.oms.service.impl;

import com.dubbo.gmall.oms.entity.OrderItem;
import com.dubbo.gmall.oms.mapper.OrderItemMapper;
import com.dubbo.gmall.oms.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
