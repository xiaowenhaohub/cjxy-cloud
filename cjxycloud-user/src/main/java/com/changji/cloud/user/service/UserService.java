package com.changji.cloud.user.service;

import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.user.model.User;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 3:03 2022/8/23
 * @ Modified By：
 */
public interface UserService {

    /**
     * 保存用户信息
     * @param userDTO
     */
    void save(UserDTO userDTO);

    /**
     * 通过学号查询账号
     * @return
     */
    User queryUserByAccount();
}
