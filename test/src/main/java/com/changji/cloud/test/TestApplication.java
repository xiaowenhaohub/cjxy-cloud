package com.changji.cloud.test;

import com.changji.cloud.common.security.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:21 2022/8/19
 * @ Modified By：
 */
@SpringBootApplication()
@EnableCustomConfig
@ComponentScan(basePackages = {"com.changji.cloud"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
