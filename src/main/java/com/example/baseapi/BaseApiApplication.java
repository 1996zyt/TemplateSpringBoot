package com.example.baseapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.baseapi.dao")
@SpringBootApplication
public class BaseApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }
}
