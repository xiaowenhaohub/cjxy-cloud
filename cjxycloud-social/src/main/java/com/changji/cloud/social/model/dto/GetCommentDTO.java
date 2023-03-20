package com.changji.cloud.social.model.dto;

import com.changji.cloud.common.core.model.Page;

/**
 * @author 小问号
 * @date 2023/3/17 17
 * description
 */

public class GetCommentDTO extends Page {

    private Long friendCircleId;

    private Long rootCommentId;

    public Long getFriendCircleId() {
        return friendCircleId;
    }

    public void setFriendCircleId(Long friendCircleId) {
        this.friendCircleId = friendCircleId;
    }

    public Long getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    @Override
    public String toString() {
        return "GetCommentDTO{" +
                "friendCircleId=" + friendCircleId +
                ", rootCommentId=" + rootCommentId +
                '}';
    }
}
