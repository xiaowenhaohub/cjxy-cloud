package com.changji.cloud.api.leaf.enums;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 19:36 2022/8/20
 * @ Modified By：
 */
public enum LeafKeyEnum {

    /**
     * 获取auth uid
     */
    AUTH_UID_KEY("cjxycloud-auth"),

    /**
     * 获取user_id
     */
    USER_ID_KEY("cjxycloud-user")
    ;

    private String value;

    private LeafKeyEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
