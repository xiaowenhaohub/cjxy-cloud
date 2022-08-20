package com.changji.cloud.auth.controller;

import com.changji.cloud.api.auth.vo.TokenInfoVO;
import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:22 2022/8/20
 * @ Modified By：
 */
@RestController
public class TokenController {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ServerResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthenticationDTO authenticationDTO) {

        LoginUser loginUser = authAccountService.login(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        Map<String, Object> response = tokenService.createToken(loginUser);
        return ServerResponseEntity.success(response);
    }
}
