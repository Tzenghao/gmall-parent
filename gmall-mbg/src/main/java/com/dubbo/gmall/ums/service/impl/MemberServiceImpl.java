package com.dubbo.gmall.ums.service.impl;

import com.dubbo.gmall.ums.entity.Member;
import com.dubbo.gmall.ums.mapper.MemberMapper;
import com.dubbo.gmall.ums.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
