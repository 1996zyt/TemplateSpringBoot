package com.example.baseapi.response;

public class UserLoginResponse extends BaseResponse{
    private String token;

    public UserLoginResponse(String token) {
        this.token = token;
    }
}
