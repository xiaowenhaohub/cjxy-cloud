package com.changji.cloud.social.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @author 小问号
 * @date 2022/9/23 10
 * description
 */

public class FriendCircleComment extends BaseModel {

    private Long commentId;

    private Long friendCircleId;

    private Long userId;

    private String content;

    private Integer likedCount;

    private Long rootCommentId;

    private Long toCommentId;

    public Long getCommentId() {
        return commentId;
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
}
