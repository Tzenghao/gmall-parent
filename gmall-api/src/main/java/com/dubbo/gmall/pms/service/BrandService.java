package com.dubbo.gmall.pms.service;

import com.dubbo.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dubbo.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Zh
 * @since 2020-01-09
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInf(String keyword, Integer pageNum, Integer pageSize);
}
