package com.changji.cloud.social.mapper;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.social.model.FriendCircleMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */
@Repository
public interface FriendCircleMessageMapper {

    /**
     * 查询未删除朋友圈总数
     * @return
     */
    @Select("select count(*) from  friend_circle_message where is_delete = 1")
    Integer getFriendCircleMessageCount();

    /**
     * 保存朋友圈
     * @param friendCircleMessage
     */
    void save(@Param("friendCircleMessage") FriendCircleMessage friendCircleMessage);

    /**
     * 获取朋友圈动态
     * @return
     */
    List<FriendCircleMessage> getFriendCircle(@Param("userId") Long userId);

    /**
     * 根据id获取朋友圈
     * @param id
     * @return
     */
    FriendCircleMessage getFriendCircleMessageById(@Param("id") Long id);

    /**
     * 更新朋友圈信息
     * @param friendCircleMessage
     * @return
     */
    int updateFriendCircleMessage(@Param("friendCircleMessage") FriendCircleMessage friendCircleMessage);

    /**
     * 删除指定朋友圈
     * @param friendCircleId
     * @param userId
     */
    int deleteByFriendCircleIdAndUserId(@Param("friendCircleId") Long friendCircleId,@Param("userId") Long userId);
}
