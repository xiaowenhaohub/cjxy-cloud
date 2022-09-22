package com.changji.cloud.user.mapper;

import com.changji.cloud.user.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 3:08 2022/8/23
 * @ Modified By：
 */
public interface UsersMapper {

    /**
     * 保存用户信息
     * @param user
     */
    void save(@Param("user") User user);


    /**
     * 通过学号查询用户
     * @param account
     * @return
     */
    User queryUserByAccount(@Param("account") String account);

    /**
     * 通过userid查询用户
     * @param userId
     * @return
     */
    User queryUserById(@Param("userId") Long userId);
}
