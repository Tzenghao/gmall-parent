package com.dubbo.gmall.admin.ums.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.gmall.to.CommonResult;
import com.dubbo.gmall.ums.entity.MemberLevel;
import com.dubbo.gmall.ums.service.MemberLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mode class
 *
 * @Author ZengHao
 * @Date 2020/1/14 17:18
 */
@CrossOrigin
@RestController
@Slf4j
public class UmsMemberLevelController {
    @Reference
    private MemberLevelService memberLevelService;
    /**
     * 查出所有会员等级
     * @return
     */
    @ResponseBody
    @GetMapping("/memberLevel/list")
    public Object memberLevelList(){
        List<MemberLevel> levels = memberLevelService.list();
        return new CommonResult().success(levels);
    }
}
