package com.changji.cloud.social.repository;

import com.changji.cloud.social.model.FriendCircleLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/9/21 10
 * description
 */
public interface FriendCircleLikeRepository {
    /**
     * 保存一个点赞数据
     * @param friendCircleLike
     * @return
     */
    FriendCircleLike save(@Param("friendCircleLike") FriendCircleLike friendCircleLike);

    /**
     * 更新点赞信息
     * @param friendCircleLike
     * @return
     */
    FriendCircleLike update(FriendCircleLike friendCircleLike);

    /**
     * 批量保存点赞数据
     * @param list
     * @return
     */
    List<FriendCircleLike> saveAll(List<FriendCircleLike> list);

    /**
     * 根据朋友圈id查询点赞列表（即查询都谁给这个人点赞过）
     * @param friendCircleId
     * @param status
     * @param pageable
     * @return
     */
    Page<FriendCircleLike> findByLikedFriendCircleIdAndStatus(Long friendCircleId, Integer status, Pageable pageable);


    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param userId
     * @param status
     * @return
     */
    Page<FriendCircleLike> findByUserIdAndStatus(Long userId, Integer status, Pageable pageable);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param friendCircleId
     * @param userId
     * @return
     */
    FriendCircleLike findByLikedFriendCircleIdAndUserId( Long friendCircleId, Long userId);

}
