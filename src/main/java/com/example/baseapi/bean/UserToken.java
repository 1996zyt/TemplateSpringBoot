package com.example.baseapi.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserToken {
    private long userId;

    private String token;

    private Date updateTime;

    private Date expireTime;
}
