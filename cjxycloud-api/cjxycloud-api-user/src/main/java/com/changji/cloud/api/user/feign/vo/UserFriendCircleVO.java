package com.changji.cloud.api.user.feign.vo;

/**
 * @author 小问号
 * @date 2022/9/22 16
 * description
 */

public class UserFriendCircleVO {

    private String nickName;

    private String picture;

    public UserFriendCircleVO() {
    }

    public UserFriendCircleVO(String nickName, String picture) {
        this.nickName = nickName;
        this.picture = picture;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "UserFriendCircleVO{" +
                "nickName='" + nickName + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
