package com.changji.cloud.auth.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:22 2022/8/21
 * @ Modified By：
 */
@RestController
public class TestController {

    @PostMapping("test")
    @RequiresPermissions("system:user:list")
    public ServerResponseEntity<Object> test() {
        return ServerResponseEntity.success("test success");
    }
}
