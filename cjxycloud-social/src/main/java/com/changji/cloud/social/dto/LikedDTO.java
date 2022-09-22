package com.changji.cloud.social.dto;

/**
 * @author 小问号
 * @date 2022/9/22 09
 * description
 */

public class LikedDTO {
    private String key;

    private Integer value;

    public LikedDTO() {
    }

    public LikedDTO(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        String[] split = key.split("::");
        return Long.valueOf(split[0]);
//        Long userId = Long.valueOf(split[1]);
    }

    public Integer getValue() {
        return value;
    }

}
