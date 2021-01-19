package com.example.baseapi.response;

import com.example.baseapi.bean.TemplateBean;

import java.util.List;

public class TemplateResponse extends BaseResponse {
    private List<TemplateBean> list;

    public TemplateResponse(List<TemplateBean> list) {
        this.list = list;
    }

    public List<TemplateBean> getList() {
        return list;
    }

    public void setList(List<TemplateBean> list) {
        this.list = list;
    }
}
