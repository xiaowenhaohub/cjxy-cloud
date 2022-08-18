package com.changji.cloud.common.core.exception.auth;

import org.apache.commons.lang.StringUtils;

/**
 * @author 小问号
 * @date 2022/8/18 17
 * description 未能通过的权限认证异常
 */

public class NotPermissionException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotPermissionException(String permission)
    {
        super(permission);
    }

    public NotPermissionException(String[] permissions)
    {
        super(StringUtils.join(permissions, ","));
    }
}
