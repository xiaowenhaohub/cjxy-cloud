package com.changji.cloud.common.datasource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 12:44 2022/7/24
 * @ Modified By：
 */

@Configuration
@MapperScan("com.changji.cloud.**.mapper")
public class MybatisConfig {
}
