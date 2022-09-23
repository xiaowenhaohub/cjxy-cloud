package com.changji.cloud.social.mapper;

import com.changji.cloud.social.model.FriendCircleComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 小问号
 * @date 2022/9/23 10
 * description
 */

@Repository
public interface FriendCircleCommentMapper {

    /**
     * 保存评论
     * @param friendCircleComment
     */
    void save(@Param("friendCircleComment") FriendCircleComment friendCircleComment);
}
