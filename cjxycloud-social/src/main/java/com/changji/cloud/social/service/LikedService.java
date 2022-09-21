package com.changji.cloud.social.service;

import com.changji.cloud.social.model.FriendCircleLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/9/21 09
 * description
 */
public interface LikedService {

    /**
     * 保存点赞记录
     * @param friendCircleLike
     * @return
     */
    FriendCircleLike save(FriendCircleLike friendCircleLike);

    /**
     * 批量保存或修改
     * @param list
     */
    List<FriendCircleLike> saveAll(List<FriendCircleLike> list);


    /**
     * 根据被点赞朋友圈的id查询点赞列表（即查询都谁给这个朋友圈点赞过）
     * @param likedFriendCircleId 被点赞人的id
     * @param pageable
     * @return
     */
    Page<FriendCircleLike> getLikedListByLikedFriendCircleId(Long likedFriendCircleId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param userId
     * @param pageable
     * @return
     */
    Page<FriendCircleLike> getLikedListByUserId(Long userId, Pageable pageable);

    /**
     * 通过被点赞朋友圈和点赞人id查询是否存在点赞记录
     * @param likedFriendCircleId
     * @param userId
     * @return
     */
    FriendCircleLike getByLikedFriendCircleIdAndUserId(Long likedFriendCircleId, Long userId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();
}
