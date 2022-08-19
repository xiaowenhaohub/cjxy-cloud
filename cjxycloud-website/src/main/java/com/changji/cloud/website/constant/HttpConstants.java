package com.changji.cloud.website.constant;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:28 2022/8/20
 * @ Modified By：
 */
public enum HttpConstants {

    /**
     * 基础url
     */
    BASE_URL("https://219.247.64.162/jsxsd"),

    /**
     * 登录url
     */
    LOGIN_URL("/xk/LoginToXk")
    ;

    private String value;

    private HttpConstants(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
