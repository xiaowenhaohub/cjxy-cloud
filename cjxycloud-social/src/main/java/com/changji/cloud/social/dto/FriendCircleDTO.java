package com.changji.cloud.social.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author 小问号
 * @date 2022/9/20 14
 * description
 */

public class FriendCircleDTO {

    @ApiModelProperty("正文")
    @NotBlank(message = "正文不能为空")
    private String content;

    @ApiModelProperty("图片")
    private String picture;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
