package com.dubbo.gmall.pms.service.impl;

import com.dubbo.gmall.pms.entity.Comment;
import com.dubbo.gmall.pms.mapper.CommentMapper;
import com.dubbo.gmall.pms.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
