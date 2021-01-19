package com.example.baseapi.service;

import com.example.baseapi.bean.User;
import com.example.baseapi.bean.UserToken;
import com.example.baseapi.config.TemplateException;
import com.example.baseapi.dao.UserMapper;
import com.example.baseapi.dao.UserTokenMapper;
import com.example.baseapi.request.UserUpdateRequest;
import com.example.baseapi.util.MD5Util;
import com.example.baseapi.util.NumberUtil;
import com.example.baseapi.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserTokenMapper userTokenMapper;

    //注册
    public String register(String userName, String password) {
        if (userMapper.selectByUserName(userName) != null) {
            return "用户名已存在";
        }
        User registerUser = new User();
        registerUser.setUserName(userName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);

        registerUser.setCreateTime(new Date());
        if (userMapper.insert(registerUser) > 0) {
            return "注册成功";
        }else {
            return "数据库错误";
        }
    }

    //登录
    public String login(String userName, String passwordMD5) {
        User user = userMapper.selectByUserNameAndPassWord(userName, passwordMD5);
        if (user != null) {
            if (user.isLocked()) {
                return "账号已锁定";
            }
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
            UserToken userToken = userTokenMapper.select(user.getUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (userToken == null) {
                userToken = new UserToken();
                userToken.setUserId(user.getUserId());
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //新增一条token数据
                if (userTokenMapper.insert(userToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //更新
                if (userTokenMapper.update(userToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }

        }
        return "登录失败";
    }

    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return TokenUtil.genToken(src);
    }

    public Boolean updateUserInfo(UserUpdateRequest userUpdateRequest, Long userId) {
        User user = userMapper.select(userId);
        if (user == null) {
            TemplateException.exception("用户不存在");
        }

        //若密码为空字符，则表明用户不打算修改密码，使用原密码保存
        if (!MD5Util.MD5Encode("", "UTF-8").equals(userUpdateRequest.getPasswordMd5())){
            user.setPasswordMd5(userUpdateRequest.getPasswordMd5());
        }

        if (userMapper.update(user) > 0) {
            return true;
        }
        return false;
    }

    public Boolean logout(Long userId) {
        return userTokenMapper.delete(userId) > 0;
    }
}
