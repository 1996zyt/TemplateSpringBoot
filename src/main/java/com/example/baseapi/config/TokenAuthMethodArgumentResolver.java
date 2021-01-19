package com.example.baseapi.config;

import com.example.baseapi.bean.User;
import com.example.baseapi.bean.UserToken;
import com.example.baseapi.dao.UserMapper;
import com.example.baseapi.dao.UserTokenMapper;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

@Component
public class TokenAuthMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserTokenMapper userTokenMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(TokenAuth.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (methodParameter.getParameterAnnotation(TokenAuth.class) instanceof TokenAuth) {
            User user = null;
            String token = nativeWebRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                UserToken userToken = userTokenMapper.selectByToken(token);
                if (userToken == null || userToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    TemplateException.exception("token错误或过期");
                }
                user = userMapper.select(userToken.getUserId());
                if (user == null) {
                    TemplateException.exception("用户不存在");
                }
                if (user.isLocked()) {
                    TemplateException.exception("用户已锁定");
                }
                return user;
            } else {
                TemplateException.exception("用户不存在");
            }
        }
        return null;
    }
}
