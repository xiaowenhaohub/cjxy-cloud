package com.changji.cloud.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:10 2022/8/20
 * @ Modified By：
 */
@SpringBootApplication(scanBasePackages = {"com.changji.cloud"})
@EnableFeignClients
public class WebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }
}
