package com.example.baseapi.controller;

import com.example.baseapi.response.BaseResponse;
import com.example.baseapi.response.TemplateResponse;
import com.example.baseapi.service.TemplateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TemplateController {
    @Resource
    private TemplateService templateService;

    //获取模板信息
    @PostMapping("/template")
    public BaseResponse template() {
        TemplateResponse templateResponse = templateService.getTemplate();
        return templateResponse;
    }
}
