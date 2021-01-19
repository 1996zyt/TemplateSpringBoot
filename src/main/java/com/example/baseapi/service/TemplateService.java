package com.example.baseapi.service;

import com.example.baseapi.bean.TemplateBean;
import com.example.baseapi.config.TemplateException;
import com.example.baseapi.dao.TemplateMapper;
import com.example.baseapi.response.TemplateResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateService {
    @Resource
    private TemplateMapper templateMapper;

    public TemplateResponse getTemplate() {
        List<TemplateBean> templateBeans = templateMapper.select();
        if (!CollectionUtils.isEmpty(templateBeans)) {
            return new TemplateResponse(templateBeans);
        } else {
            TemplateException.exception("查询失败");
            return null;
        }
    }
}
