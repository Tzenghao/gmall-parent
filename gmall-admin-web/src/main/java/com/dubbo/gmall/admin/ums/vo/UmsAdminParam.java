package com.dubbo.gmall.admin.ums.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


/**
 * 用户登录参数
 */
@Getter
@Setter
@ToString
public class UmsAdminParam {
    @Length(min = 6,max = 18,message = "用户名长度必须是6-18位")
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty
    @ApiModelProperty(value = "用户头像")
    private String icon;
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "备注")
    private String note;
}
