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
}
