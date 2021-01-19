package com.example.baseapi.controller;

import com.example.baseapi.bean.User;
import com.example.baseapi.config.TokenAuth;
import com.example.baseapi.request.UserLoginRequest;
import com.example.baseapi.request.UserRegisterRequest;
import com.example.baseapi.request.UserUpdateRequest;
import com.example.baseapi.response.BaseResponse;
import com.example.baseapi.response.UserLoginResponse;
import com.example.baseapi.response.UserRegisterResponse;
import com.example.baseapi.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    //注册
    @PostMapping("/user/register")
    public BaseResponse register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        String registerResult = userService.register(userRegisterRequest.getUserName(), userRegisterRequest.getPasswordMd5());

        //注册成功
        if ("注册成功".equals(registerResult)) {
            return new UserRegisterResponse(registerResult);
        }
        //注册失败
        BaseResponse failResponse = new BaseResponse();
        failResponse.setSuccess(false);
        failResponse.setMessage(registerResult);
        return failResponse;
    }

    //登录
    @PostMapping("/user/login")
    public BaseResponse login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        String loginResult = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPasswordMd5());
        //登录成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == 32) {
            return new UserLoginResponse(loginResult);
        }
        //登录失败
        BaseResponse failResponse = new BaseResponse();
        failResponse.setSuccess(false);
        failResponse.setMessage(loginResult);
        return failResponse;
    }

    //修改
    @PostMapping("/user/info")
    public BaseResponse updateInfo(@RequestBody UserUpdateRequest userUpdateRequest,
                                   @TokenAuth User loginUser) {
        Boolean flag = userService.updateUserInfo(userUpdateRequest, loginUser.getUserId());
        if (flag) {
            //返回成功
            return new BaseResponse();
        } else {
            //返回失败
            BaseResponse failResponse = new BaseResponse();
            failResponse.setSuccess(false);
            return failResponse;
        }
    }

    //退出
    @PostMapping("/user/logout")
    public BaseResponse logout(@TokenAuth User loginUser) {
        Boolean logoutResult = userService.logout(loginUser.getUserId());
        //退出成功
        if (logoutResult) {
            return new BaseResponse();
        }
        //退出失败
        BaseResponse failResponse = new BaseResponse();
        failResponse.setSuccess(false);
        return failResponse;
    }
}
