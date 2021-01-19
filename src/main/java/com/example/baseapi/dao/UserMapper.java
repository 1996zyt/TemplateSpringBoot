package com.example.baseapi.dao;

import com.example.baseapi.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int delete(long userId);

    int insert(User user);

    User select(long userId);

    int update(User user);

    User selectByUserName(String userName);

    User selectByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);
}
