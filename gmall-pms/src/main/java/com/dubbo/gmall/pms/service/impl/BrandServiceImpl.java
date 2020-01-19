package com.dubbo.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dubbo.gmall.pms.entity.Brand;
import com.dubbo.gmall.pms.mapper.BrandMapper;
import com.dubbo.gmall.pms.service.BrandService;
import com.dubbo.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageInfoVo brandPageInf(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<Brand>().like(!StringUtils.isEmpty(keyword),"name", keyword);
        IPage<Brand> page = brandMapper.selectPage(new Page<Brand>(pageNum.longValue(), pageSize.longValue()), wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(page.getTotal(),page.getPages(),pageNum.longValue(),page.getRecords(),page.getCurrent());
        return pageInfoVo;
    }
}
