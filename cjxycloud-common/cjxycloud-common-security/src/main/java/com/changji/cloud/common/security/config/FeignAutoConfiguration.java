package com.changji.cloud.common.security.config;

import com.changji.cloud.common.security.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:40 2022/8/23
 * @ Modified By：
 */

@Configuration
public class FeignAutoConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
