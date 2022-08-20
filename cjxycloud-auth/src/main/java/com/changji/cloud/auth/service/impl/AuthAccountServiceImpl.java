package com.changji.cloud.auth.service.impl;

import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.auth.mapper.AuthAccountMapper;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:33 2022/8/20
 * @ Modified By：
 */
@Service
public class AuthAccountServiceImpl implements AuthAccountService {

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Override
    public AuthAccount login(String username, String password) {

        AuthAccount authAccount = authAccountMapper.queryByUserName(username);

        if (authAccount == null) {

        }


        return null;
    }
}
