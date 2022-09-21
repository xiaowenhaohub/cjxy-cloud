package com.changji.cloud.social.service;

import com.changji.cloud.social.dto.LikedCountDTO;
import com.changji.cloud.social.model.FriendCircleLike;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/9/21 08
 * description
 */
public interface LikedRedisService {

    /**
     * 点赞。状态为1
     * @param likedFriendCircleId
     * @param userId
     */
    void saveLiked2Redis(String likedFriendCircleId, String userId);

    /**
     * 取消点赞。将状态改变为0
     * @param likedFriendCircleId
     * @param userId
     */
    void unlikeFromRedis(String likedFriendCircleId, String userId);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedFriendCircleId
     * @param userId
     */
    void deleteLikedFromRedis(String likedFriendCircleId, String userId);

    /**
     * 该朋友圈的点赞数加1
     * @param likedFriendCircleId
     */
    void incrementLikedCount(String likedFriendCircleId);

    /**
     * 该朋友圈的点赞数减1
     * @param likedFriendCircleId
     */
    void decrementLikedCount(String likedFriendCircleId);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<FriendCircleLike> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<LikedCountDTO> getLikedCountFromRedis();
}
