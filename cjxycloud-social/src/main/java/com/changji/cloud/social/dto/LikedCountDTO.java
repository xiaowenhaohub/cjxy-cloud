package com.changji.cloud.social.dto;

/**
 * @author 小问号
 * @date 2022/9/21 09
 * description
 */

public class LikedCountDTO {

    private String key;

    private Integer value;

    public LikedCountDTO() {
    }

    public LikedCountDTO(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
