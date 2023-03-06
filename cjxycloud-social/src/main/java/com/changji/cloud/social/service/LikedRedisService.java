package com.changji.cloud.social.service;

import com.changji.cloud.social.model.dto.LikedCountDTO;
import com.changji.cloud.social.model.dto.LikedDTO;
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
    void saveLiked2Redis(Long likedFriendCircleId, Long userId);

    /**
     * 取消点赞。将状态改变为0
     * @param likedFriendCircleId
     * @param userId
     */
    void unlikeFromRedis(Long likedFriendCircleId, Long userId);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedFriendCircleId
     * @param userId
     */
    void deleteLikedFromRedis(Long likedFriendCircleId, Long userId);

    /**
     * 该朋友圈的点赞数加1
     * @param likedFriendCircleId
     */
    void incrementLikedCount(Long likedFriendCircleId);

    /**
     * 该朋友圈的点赞数减1
     * @param likedFriendCircleId
     */
    void decrementLikedCount(Long likedFriendCircleId);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<FriendCircleLike> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的一个点赞数据
     * @return
     */
    LikedDTO getOneLikedDataFromRedis(String key);

    /**
     * 获取Redis中存储的所有点赞对象节点
     * @return
     */
    List<LikedCountDTO> getAllLikedCountFromRedis();

    /**
     * 获取一个朋友圈的点赞数
     * @param friendCircleId
     * @return
     */
    Integer getOneLikedCountFromRedis(Long friendCircleId);
}
