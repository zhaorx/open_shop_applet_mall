package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 启动入口
 * @Date 2019/12/24
 * @Created by 67068
 */
@SpringBootApplication(scanBasePackages = "com.platform")
public class ApiApplication {


    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class,args);

    }
}
