package com.kinghao.dian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.kinghao.dian.mapper")
public class DianApplication {

    public static void main(String[] args) {
        SpringApplication.run(DianApplication.class, args);
    }

}
