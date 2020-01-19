package com.dubbo.gmall.admin.oss.controller;


import com.dubbo.gmall.admin.oss.compenent.OssCompent;
import com.dubbo.gmall.to.CommonResult;
import com.dubbo.gmall.to.OssPolicyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss相关操作接口
 */
@Controller
//@CrossOrigin(origins = "www.baidu.com")  //设置哪些地址或ip可以跨域访问到这个controller
@CrossOrigin
@Api(tags = "OssController",description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
	@Autowired
	private OssCompent ossCompent;

	@ApiOperation(value = "oss上传签名生成")
	@GetMapping(value = "/policy")
	@ResponseBody
	public Object policy() {
		OssPolicyResult result = ossCompent.policy();
		return new CommonResult().success(result);
	}

}
