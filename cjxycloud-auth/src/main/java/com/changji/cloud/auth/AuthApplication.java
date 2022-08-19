package com.changji.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 小问号
 * @date 2022/8/19 15
 * description
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.changji.cloud"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
