package com.changji.cloud.auth.controller;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:22 2022/8/21
 * @ Modified By：
 */
@RestController
public class TestController {

    @Autowired
    private WebsiteFeignClient websiteFeignClient;

    @GetMapping("test/{username}")
    public ServerResponseEntity<Object> test(@PathVariable("username") String username) {

        System.out.println("fall back test start:" + username);
        ServerResponseEntity<Object> responseEntity = websiteFeignClient.testFallback(username);
        return responseEntity;
    }
}
