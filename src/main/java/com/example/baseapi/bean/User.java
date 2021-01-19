package com.example.baseapi.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private long userId;

    private String userName;

    private String passwordMd5;

    private boolean isDeleted;

    private boolean isLocked;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
