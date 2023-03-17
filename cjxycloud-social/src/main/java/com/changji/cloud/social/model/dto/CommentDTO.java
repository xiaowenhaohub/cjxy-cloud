package com.changji.cloud.social.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author 小问号
 * @date 2023/3/17 16
 * description
 */

public class CommentDTO {

    private Long friendCircleId;

    @NotBlank(message = "内容不能为空")
    private String content;

    private Long rootCommentId;

    private Long toCommentId;

    public Long getFriendCircleId() {
        return friendCircleId;
    }

    public void setFriendCircleId(Long friendCircleId) {
        this.friendCircleId = friendCircleId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
