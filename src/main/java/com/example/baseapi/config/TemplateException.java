package com.example.baseapi.config;

public class TemplateException extends RuntimeException{
    public TemplateException(String message) {
        super(message);
    }

    public static void exception(String message) {
        throw new TemplateException(message);
    }
}
