package com.changji.cloud.social.model.vo;

import java.util.Date;

/**
 * @author 小问号
 * @date 2023/3/20 09
 * description
 */

public class CommentVO {

    private Long commentId;

    private Long friendCircleId;

    private String account;

    private String content;

    private Integer likedCount;

    private Long rootCommentId;

    private Long toCommentId;

    private String nickName;

    private String avatar;

    private Integer commentCount;

    private Date createTime;


    public Long getCommentId() {
        return commentId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getFriendCircleId() {
        return friendCircleId;
    }

    public void setFriendCircleId(Long friendCircleId) {
        this.friendCircleId = friendCircleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public Long getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public Long getToCommentId() {
        return toCommentId;
    }

    public void setToCommentId(Long toCommentId) {
        this.toCommentId = toCommentId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId=" + commentId +
                ", friendCircleId=" + friendCircleId +
                ", account='" + account + '\'' +
                ", content='" + content + '\'' +
                ", likedCount=" + likedCount +
                ", rootCommentId=" + rootCommentId +
                ", toCommentId=" + toCommentId +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
