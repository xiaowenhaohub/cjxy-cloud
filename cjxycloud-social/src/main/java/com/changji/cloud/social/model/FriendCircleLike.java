package com.changji.cloud.social.model;

import com.changji.cloud.common.core.model.BaseModel;
import com.changji.cloud.social.enums.LikedStatusEnum;

/**
 * @author 小问号
 * @date 2022/9/21 08
 * description
 */

public class FriendCircleLike extends BaseModel {

    /**
     * 主键
     */
    private Long id;

    /**
     * 被点赞的朋友圈id
     */
    private Long friendCircleId;

    /**
     * 点赞的用户id
     */
    private Long userId;

    /**
     * 点赞状态，0取消，1点赞
     */
    private Integer status = LikedStatusEnum.UNLIKE.getCode();;


    public FriendCircleLike() {
    }

    public FriendCircleLike(Long friendCircleId, Long userId, Integer status) {
        this.friendCircleId = friendCircleId;
        this.userId = userId;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
