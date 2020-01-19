package com.dubbo.gmall.ums.service;

import com.dubbo.gmall.ums.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface AdminService extends IService<Admin> {

    Admin login(String username, String password);

    Admin getUserInfo(String userName);
}
