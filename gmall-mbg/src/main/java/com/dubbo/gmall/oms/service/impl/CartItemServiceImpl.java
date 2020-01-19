package com.dubbo.gmall.oms.service.impl;

import com.dubbo.gmall.oms.entity.CartItem;
import com.dubbo.gmall.oms.mapper.CartItemMapper;
import com.dubbo.gmall.oms.service.CartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
