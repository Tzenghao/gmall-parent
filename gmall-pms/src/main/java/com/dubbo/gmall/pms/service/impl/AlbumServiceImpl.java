package com.dubbo.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.Album;
import com.dubbo.gmall.pms.mapper.AlbumMapper;
import com.dubbo.gmall.pms.service.AlbumService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 相册表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

}
