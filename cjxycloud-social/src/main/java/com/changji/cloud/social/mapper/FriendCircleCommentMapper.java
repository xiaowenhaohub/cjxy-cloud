package com.changji.cloud.social.mapper;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.social.model.FriendCircleComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    int save(@Param("friendCircleComment") FriendCircleComment friendCircleComment);

    /**
     * 通过id查询评论
     * @param commentId
     * @return
     */
    FriendCircleComment findById(@Param("commentId") Long commentId, @Param("friendId") Long friendId);


    /**
     * 查询评论
     * @param friendId
     * @return
     */
    List<FriendCircleComment> getComments(@Param("friendId") Long friendId,@Param("rootCommentId") Long rootCommentId, @Param("page")Page page);

    /**
     * 查询评论数量
     * @param friendCircleId
     * @return
     */
    int count(@Param("friendCircleId") Long friendCircleId);

    /**
     * 查询顶级评论的评论数量
     * @param commentId
     * @return
     */
    int countByCommentId(@Param("commentId")Long commentId);

}
