package com.changji.cloud.common.core.exception.auth;

/**
 * @author 小问号
 * @date 2022/8/18 17
 * description 未登录异常
 */

public class NotLoginException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotLoginException(String message)
    {
        super(message);
    }
}
