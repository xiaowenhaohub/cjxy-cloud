package com.changji.cloud.common.core.exception;

import org.apache.commons.lang.StringUtils;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:22 2022/8/20
 * @ Modified By： 空参数异常
 */
public class NullParamException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NullParamException(String permission) {
        super(permission);
    }

    public NullParamException(String[] permissions) {
        super(StringUtils.join(permissions, ","));
    }
}
