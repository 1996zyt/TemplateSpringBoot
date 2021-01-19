package com.example.baseapi.config;

import com.example.baseapi.response.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class TemplateExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        BindingResult bindingResult = e.getBindingResult();
        response.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        BindingResult bindingResult = e.getBindingResult();
        response.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return response;
    }

    @ExceptionHandler(TemplateException.class)
    public Object handleException(java.lang.Exception e, HttpServletRequest req) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        //区分是否为自定义异常
        if (e instanceof TemplateException) {
            response.setMessage(e.getMessage());
        } else {
            e.printStackTrace();
            response.setMessage("出现了未知异常");
        }
        return response;
    }
}
