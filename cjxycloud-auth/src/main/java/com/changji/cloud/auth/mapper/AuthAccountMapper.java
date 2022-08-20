package com.changji.cloud.auth.mapper;

import com.changji.cloud.auth.model.AuthAccount;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

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


    /**
     * 保存用户
     * @param authAccount
     */
    void save(@Param("authAccount") AuthAccount authAccount);


    /**
     * 根据uid查询 用户权限列表
     * @param uid
     * @return
     */
    List<String> queryUserMenuByUid(@Param("uid") Long uid);

    /**
     * 设置用户角色
     * @param uid
     * @param roleId
     */
    void saveAuthRole(@Param("uid") Long uid, @Param("roleId") Long roleId);

}
