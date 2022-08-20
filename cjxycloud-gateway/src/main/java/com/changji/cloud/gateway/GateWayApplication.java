package com.changji.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小问号
 * @date 2022/8/19 17
 * description
 */


@SpringBootApplication(scanBasePackages = {"com.changji.cloud"})
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
