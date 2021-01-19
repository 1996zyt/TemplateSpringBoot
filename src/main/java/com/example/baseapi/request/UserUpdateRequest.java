package com.example.baseapi.request;

import lombok.Data;

@Data
public class UserUpdateRequest extends BaseRequest{
    //("用户密码(需要MD5加密)")
    private String passwordMd5;
}
