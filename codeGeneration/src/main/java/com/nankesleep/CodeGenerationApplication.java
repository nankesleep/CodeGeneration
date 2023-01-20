package com.nankesleep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.nankesleep.mapper")
@EnableSwagger2
public class CodeGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenerationApplication.class,args);
    }
}