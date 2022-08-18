package com.changji.cloud.common.security.annotation;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:26 2022/8/19
 * @ Modified By： 权限注解的验证模式
 */
public enum Logical {

    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR
}
