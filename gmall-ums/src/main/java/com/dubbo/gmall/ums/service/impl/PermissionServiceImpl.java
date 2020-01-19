package com.dubbo.gmall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.ums.entity.Permission;
import com.dubbo.gmall.ums.mapper.PermissionMapper;
import com.dubbo.gmall.ums.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
