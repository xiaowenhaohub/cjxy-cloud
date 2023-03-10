package com.changji.cloud.user.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.user.model.User;
import com.changji.cloud.user.service.UserService;
import com.changji.cloud.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:34 2022/8/24
 * @ Modified By：
 */

@RestController
@Api("用户")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @RequiresPermissions("common:user:query")
    @ApiOperation("查询个人信息")
    @Log(title = "用户模块")
    public ServerResponseEntity<UserVO> queryUserDetail() {
        UserVO userVO = userService.queryMeDetails();
        return ServerResponseEntity.success(userVO);
    }

    @GetMapping("/search/{account}")
    @RequiresPermissions("common:user:query")
    @ApiOperation("搜索用户")
    @Log(title = "用户模块")
    public ServerResponseEntity<UserVO> searchUser(@PathVariable("account") String account) {
        UserVO userVO = userService.queryUserByAccount(account);
        return ServerResponseEntity.success("搜索成功",userVO);
    }




}
