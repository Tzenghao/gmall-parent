package com.dubbo.gmall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.ums.entity.Role;
import com.dubbo.gmall.ums.mapper.RoleMapper;
import com.dubbo.gmall.ums.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
