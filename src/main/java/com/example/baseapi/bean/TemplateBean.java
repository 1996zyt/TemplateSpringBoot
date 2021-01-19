package com.example.baseapi.bean;

import lombok.Data;

@Data
public class TemplateBean {
    private String templateText;
    private String templateImgUrl;

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTemplateImgUrl() {
        return templateImgUrl;
    }

    public void setTemplateImgUrl(String templateImgUrl) {
        this.templateImgUrl = templateImgUrl;
    }
}
