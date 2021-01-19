package com.example.baseapi.response;

public class UserRegisterResponse extends BaseResponse{
    private String token;

    public UserRegisterResponse(String token) {
        this.token = token;
    }
}
