package com.changji.cloud.auth.mapper;

import com.changji.cloud.auth.model.AuthAccount;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:37 2022/8/20
 * @ Modified By：
 */
public interface AuthAccountMapper {

    /**
     * 根据用户名查询用户
     * @return
     */
    AuthAccount queryByUserName(@Param("username") String username);

}
