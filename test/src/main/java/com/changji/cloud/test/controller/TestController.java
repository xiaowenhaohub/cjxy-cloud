package com.changji.cloud.test.controller;

import com.changji.cloud.common.security.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:47 2022/8/19
 * @ Modified By：
 */
@RestController
public class TestController {

    @RequiresPermissions("sys:admin")
    public void test() {
        System.out.println("aspect text...");
    }

}
