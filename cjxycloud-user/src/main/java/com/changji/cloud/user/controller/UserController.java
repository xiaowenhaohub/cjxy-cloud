package com.changji.cloud.user.controller;

import com.changji.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:34 2022/8/24
 * @ Modified By：
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;




}
