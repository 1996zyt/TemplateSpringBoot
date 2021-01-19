package com.example.baseapi.dao;

import com.example.baseapi.bean.UserToken;

public interface UserTokenMapper {

    int insert(UserToken userToken);

    int delete(long userId);

    int update(UserToken userToken);

    UserToken select(long userId);

    UserToken selectByToken(String token);

}
