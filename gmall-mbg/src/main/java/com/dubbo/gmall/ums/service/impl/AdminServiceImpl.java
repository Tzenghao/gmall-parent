package com.dubbo.gmall.ums.service.impl;

import com.dubbo.gmall.ums.entity.Admin;
import com.dubbo.gmall.ums.mapper.AdminMapper;
import com.dubbo.gmall.ums.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2020-01-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
