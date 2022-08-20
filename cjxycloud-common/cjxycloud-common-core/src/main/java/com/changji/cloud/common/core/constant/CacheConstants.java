package com.changji.cloud.common.core.constant;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:31 2022/8/21
 * @ Modified By：
 */
public class CacheConstants {

    /**
     * 缓存有效期，默认7天
     */
    public final static long EXPIRATION = 60 * 24 * 7;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";
}
