package com.changji.cloud.auth.controller;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:22 2022/8/21
 * @ Modified By：
 */
@RestController
public class TestController {

    @Autowired
    private WebsiteFeignClient websiteFeignClient;

    @PostMapping("test")
    @RequiresPermissions("system:user:list")
    public ServerResponseEntity<Object> test() {

        ServerResponseEntity<Object> responseEntity = websiteFeignClient.testFallback("1");

        return responseEntity;
    }
}
