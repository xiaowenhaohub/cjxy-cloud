package com.changji.cloud.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小问号
 * @date 2022/9/20 10
 * description
 */

@SpringBootApplication(scanBasePackages = { "com.changji.cloud" })
public class SocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }
}
