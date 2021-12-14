package com.jlm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jlm.mapper")
public class JlmApplication {
    public static void main(String[] args) {
        SpringApplication.run(JlmApplication.class, args);
    }

}
