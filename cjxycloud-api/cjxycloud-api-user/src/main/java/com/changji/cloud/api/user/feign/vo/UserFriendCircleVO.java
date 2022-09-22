package com.changji.cloud.api.user.feign.vo;

/**
 * @author 小问号
 * @date 2022/9/22 16
 * description
 */

public class UserFriendCircleVO {

    private String nickName;

    private String avatar;

    public UserFriendCircleVO() {
    }

    public UserFriendCircleVO(String nickName, String avatar) {
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
