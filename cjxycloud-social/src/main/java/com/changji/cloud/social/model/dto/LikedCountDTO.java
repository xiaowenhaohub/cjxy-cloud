package com.changji.cloud.social.model.dto;

/**
 * @author 小问号
 * @date 2022/9/21 09
 * description
 */

public class LikedCountDTO {

    private String key;

    private Integer likedCount;

    public LikedCountDTO() {
    }

    public LikedCountDTO(String key, Integer likedCount) {
        this.key = key;
        this.likedCount = likedCount;
    }

    public Long getId() {
        String[] split = key.split("::");
        return Long.valueOf(split[0]);
//        Long userId = Long.valueOf(split[1]);
    }

    public Integer getCount() {
        return likedCount;
    }
}
