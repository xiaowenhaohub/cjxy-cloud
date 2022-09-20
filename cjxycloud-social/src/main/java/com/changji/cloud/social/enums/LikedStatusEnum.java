package com.changji.cloud.social.enums;

/**
 * @author 小问号
 * @date 2022/9/20 15
 * description
 */
public enum LikedStatusEnum {

    LIKE(1, "点赞"),

    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
