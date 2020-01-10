package com.dubbo.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.Brand;
import com.dubbo.gmall.pms.mapper.BrandMapper;
import com.dubbo.gmall.pms.service.BrandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2020-01-09
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
