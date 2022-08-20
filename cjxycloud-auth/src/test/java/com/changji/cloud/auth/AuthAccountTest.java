package com.changji.cloud.auth;

import com.changji.cloud.auth.mapper.AuthAccountMapper;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import com.changji.cloud.auth.service.impl.AuthAccountServiceImpl;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:54 2022/8/20
 * @ Modified By：
 */

@SpringBootTest
public class AuthAccountTest {

    @Autowired
    private AuthAccountServiceImpl authAccountService;

    @Test
    public void test01() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2tleSI6ImMyNWU3MmI3LWZkZGUtNDMxYy05YmJlLTYzZTE4MjhjZDljYSJ9.zxozUlqM91HDPvFqkdiSxWNdeiUzrbXpLltpOqH-eA8JB2d6JB1lP5AXi43WmbknV3eH3rdpn2uBVN2LmgOAaQ";
        AuthAccount authAccount = new AuthAccount();
        authAccount.setUserId(33333l);
        authAccount.setUsername("xiaowenhao");
        LoginUser loginUser = authAccountService.toLoginUser(authAccount, null);
        System.out.println(loginUser);

    }

}
