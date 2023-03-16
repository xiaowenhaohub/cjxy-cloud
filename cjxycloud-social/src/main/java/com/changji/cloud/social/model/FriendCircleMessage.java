package com.changji.cloud.social.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */

public class FriendCircleMessage extends BaseModel {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户学号
     */
    private String account;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 图片
     */
    private String picture;

    /**
     * 位置
     */
    private String location;

    /**
     * 点赞数
     */
    private Integer likedCount;

    /**
     * 是否删除
     */
    private Integer isDelete;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "FriendCircleMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", account='" + account + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", location='" + location + '\'' +
                ", likedCount=" + likedCount +
                ", isDelete=" + isDelete +
                '}';
    }
}
