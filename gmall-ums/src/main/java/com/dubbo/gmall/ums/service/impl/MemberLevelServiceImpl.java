package com.dubbo.gmall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.ums.entity.MemberLevel;
import com.dubbo.gmall.ums.mapper.MemberLevelMapper;
import com.dubbo.gmall.ums.service.MemberLevelService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

}
