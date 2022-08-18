package com.changji.cloud.common.core.exception.auth;

import org.apache.commons.lang.StringUtils;

/**
 * @author 小问号
 * @date 2022/8/18 17
 * description 未能通过的角色认证异常
 */

public class NotRoleException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotRoleException(String role)
    {
        super(role);
    }

    public NotRoleException(String[] roles)
    {
        super(StringUtils.join(roles, ","));
    }
}
