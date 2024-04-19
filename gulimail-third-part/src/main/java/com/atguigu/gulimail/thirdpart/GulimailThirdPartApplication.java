package com.atguigu.gulimail.thirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GulimailThirdPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailThirdPartApplication.class, args);
    }

}
