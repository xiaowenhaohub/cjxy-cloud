package com.changji.cloud.social.mapper;

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

public interface FriendCircleLikeMapper {

    /**
     * 保存一个点赞数据
     * @param friendCircleLike
     * @return
     */
    int save(@Param("friendCircleLike") FriendCircleLike friendCircleLike);

    /**
     * 批量保存点赞数据
     * @param list
     * @return
     */
    int saveAll(List<FriendCircleLike> list);

    /**
     * 根据朋友圈id查询点赞列表（即查询都谁给这个人点赞过）
     * @param friendCircleId
     * @param status
     * @return
     */
    List<FriendCircleLike> findByLikedFriendCircleIdAndStatus(@Param("friendCircleId") Long friendCircleId, @Param("status") Integer status);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param userId
     * @param status
     * @return
     */
    List<FriendCircleLike> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param friendCircleId
     * @param userId
     * @return
     */
    FriendCircleLike findByLikedFriendCircleIdAndUserId(@Param("friendCircleId") Long friendCircleId, @Param("userId") Long userId);
}
