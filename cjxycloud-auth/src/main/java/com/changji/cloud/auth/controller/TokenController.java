package com.changji.cloud.auth.controller;

import com.changji.cloud.api.auth.vo.TokenInfoVO;
import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:22 2022/8/20
 * @ Modified By：
 */
@RestController
@Api(tags = "Token管理")
public class TokenController {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    @ApiOperation(value = "登录", notes = "返回token")
    public ServerResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthenticationDTO authenticationDTO) {

        LoginUser loginUser = authAccountService.login(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        Map<String, Object> response = tokenService.createToken(loginUser);
        return ServerResponseEntity.success(response);
    }


    @PostMapping("refresh")
    @ApiOperation(value = "刷新token")
    public ServerResponseEntity<Boolean> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            loginUser = authAccountService.refresh(loginUser);
            tokenService.refreshToken(loginUser);
            return ServerResponseEntity.success();
        }
        return ServerResponseEntity.showFailMsg("刷新token失败");
    }

    @DeleteMapping("logout")
    @ApiOperation("退出登录")
    public ServerResponseEntity<Boolean> logout(HttpServletRequest request) {
        String token = ServletUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            tokenService.delLoginUser(token);
        }
        return ServerResponseEntity.success();
    }
}
