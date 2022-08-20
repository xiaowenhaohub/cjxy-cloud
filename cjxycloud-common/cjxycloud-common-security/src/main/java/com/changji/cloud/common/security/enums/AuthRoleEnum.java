package com.changji.cloud.common.security.enums;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 3:11 2022/8/21
 * @ Modified By：
 */
public enum AuthRoleEnum {

    ADMIN_ROLE(1L, "超级管理员"),

    COMMON_ROLE(2L, "普通用户")
    ;

    private Long roleKey;

    private String roleName;

    private AuthRoleEnum(Long roleKey, String roleName){
        this.roleKey = roleKey;
        this.roleName= roleName;
    }

    public Long value(){
        return roleKey;
    }
}
