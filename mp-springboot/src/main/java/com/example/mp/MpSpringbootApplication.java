package com.example.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mp.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class MpSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpSpringbootApplication.class, args);
    }

}
