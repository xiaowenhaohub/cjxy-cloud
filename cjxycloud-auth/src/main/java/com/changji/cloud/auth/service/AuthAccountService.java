package com.changji.cloud.auth.service;

import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.auth.model.AuthAccount;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:31 2022/8/20
 * @ Modified By：
 */
public interface AuthAccountService {

    /**
     * 根据用户名首先从数据库查询用户，如果为空，则从教务管理系统验证用户
     * @param username
     * @param password
     * @return
     */
    AuthAccount login(String username, String password);
}
