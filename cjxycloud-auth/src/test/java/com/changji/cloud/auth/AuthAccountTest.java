package com.changji.cloud.auth;

import com.changji.cloud.auth.mapper.AuthAccountMapper;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
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
    private AuthAccountMapper authAccountMapper;

    @Test
    public void test01() {
        AuthAccount xiaowenhao = authAccountMapper.queryByUserName("xiaowenhao");
        System.out.println(xiaowenhao);
    }

}
