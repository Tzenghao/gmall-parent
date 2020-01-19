package com.dubbo.gmall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.ums.entity.Admin;
import com.dubbo.gmall.ums.mapper.AdminMapper;
import com.dubbo.gmall.ums.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<Admin> eq = new QueryWrapper<Admin>().eq("username", username)
                .eq("password", pass);
        Admin admin = adminMapper.selectOne(eq);
        return admin;
    }

    /**
     * 获取用户详情
     * @param userName
     * @return
     */
    @Override
    public Admin getUserInfo(String userName) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName));
    }
}
