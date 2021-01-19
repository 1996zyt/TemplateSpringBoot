package com.example.baseapi.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginRequest extends BaseRequest{
    //("登录名")
    @NotEmpty(message = "登录名不能为空")
    private String userName;

    //("用户密码(需要MD5加密)")
    @NotEmpty(message = "密码不能为空")
    private String passwordMd5;
}
