package com.changji.cloud.auth.controller;

import com.changji.cloud.api.auth.vo.TokenInfoVO;
import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:22 2022/8/20
 * @ Modified By：
 */
@RestController
public class TokenController {

    @PostMapping("login")
    public ServerResponseEntity<TokenInfoVO> login(AuthenticationDTO authenticationDTO) {
        return null;
    }
}
