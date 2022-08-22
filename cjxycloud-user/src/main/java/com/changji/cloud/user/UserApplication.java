package com.changji.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 2:46 2022/8/23
 * @ Modified By：
 */
@SpringBootApplication(scanBasePackages = {"com.changji.cloud"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
