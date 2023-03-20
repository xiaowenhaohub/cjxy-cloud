package com.changji.cloud.social.service;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.social.model.FriendCircleComment;
import com.changji.cloud.social.model.dto.CommentDTO;
import com.changji.cloud.social.model.dto.GetCommentDTO;
import com.changji.cloud.social.model.vo.CommentVO;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/17 16
 * description
 */

public interface CommentService {

    /**
     * 保存评论
     * @param commentDTO
     * @return
     */
    boolean save(CommentDTO commentDTO);

    /**
     * 获取顶级评论
     * @param friendCircleId
     * @param page
     * @return
     */
    List<CommentVO> getCommentList(GetCommentDTO commentDTO, Page page);

    /**
     * 获取评论数量
     * @param friendCircleId
     * @return
     */
    int getCommentCount(Long friendCircleId);
}
