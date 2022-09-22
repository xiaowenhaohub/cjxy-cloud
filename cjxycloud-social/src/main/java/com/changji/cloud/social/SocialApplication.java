package com.changji.cloud.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小问号
 * @date 2022/9/20 10
 * description
 */

@SpringBootApplication(scanBasePackages = { "com.changji.cloud" })
@EnableFeignClients(basePackages = "com.changji.cloud.api.**.feign")
public class SocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }
}
