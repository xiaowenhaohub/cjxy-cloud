package com.changji.cloud.social.service.impl;

import com.changji.cloud.api.user.feign.UserFeignClient;
import com.changji.cloud.api.user.feign.vo.UserFriendCircleVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.mapper.FriendCircleCommentMapper;
import com.changji.cloud.social.model.FriendCircleComment;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.model.dto.CommentDTO;
import com.changji.cloud.social.model.dto.GetCommentDTO;
import com.changji.cloud.social.model.vo.CommentVO;
import com.changji.cloud.social.service.CommentService;
import com.changji.cloud.social.service.FriendCircleService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/17 16
 * description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private FriendCircleCommentMapper commentMapper;

    @Autowired
    private FriendCircleService friendCircleService;

    @Autowired
    private MapperFacade mapperFacade;


    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public boolean save(CommentDTO commentDTO) {
        if (commentDTO.getFriendCircleId() == null) {
            throw new ServiceException("朋友圈id不能为空");
        }
        FriendCircleMessage friendCircleMessageById = friendCircleService.getFriendCircleMessageById(commentDTO.getFriendCircleId());
        if (StringUtils.isNull(friendCircleMessageById)) {
            throw new ServiceException("朋友圈不存在");
        }

        FriendCircleComment comment = mapperFacade.map(commentDTO, FriendCircleComment.class);
        comment.setAccount(SecurityUtils.getAccount());
        if (StringUtils.isNull(comment.getRootCommentId()) && StringUtils.isNotNull(comment.getToCommentId())) {
            throw new ServiceException("非法评论");
        }

        // 判断是否为回复顶级评论  root不为空 toComment为空
        if (StringUtils.isNotNull(comment.getRootCommentId()) && StringUtils.isNull(comment.getToCommentId())) {
            FriendCircleComment commentMapperById = commentMapper.findById(comment.getRootCommentId(), comment.getFriendCircleId());
            if (StringUtils.isNull(commentMapperById)) {
                throw new ServiceException("顶级评论不存在");
            }
        }

        //判断是否为回复次级评论  root不为空 toComment不为空
        if (StringUtils.isNotNull(comment.getRootCommentId()) && StringUtils.isNotNull(comment.getToCommentId())) {
            FriendCircleComment rootComment = commentMapper.findById(comment.getRootCommentId(),comment.getFriendCircleId());
            if (StringUtils.isNull(rootComment)) {
                throw new ServiceException("顶级评论不存在");
            }
            FriendCircleComment toComment = commentMapper.findById(comment.getToCommentId(),comment.getFriendCircleId());
            if (StringUtils.isNull(toComment)) {
                throw new ServiceException("次级评论不存在");
            }

            if (toComment.getRootCommentId() != comment.getRootCommentId()) {
                throw new ServiceException("非法评论");
            }

        }
        int i =  commentMapper.save(comment);
        if (i != 0) {
            return  true;
        }
        return false;
    }

    @Override
    public List<CommentVO> getCommentList(GetCommentDTO commentDTO, Page page) {
        if (page.getPageNum() == null) {
            page.setPageNum(0);
            page.setPageSize(10);
        }
        List<FriendCircleComment> comments = commentMapper.getComments(commentDTO.getFriendCircleId(), commentDTO.getRootCommentId(), page);
        List<CommentVO> commentVOS = new ArrayList<>();
        comments.forEach(comment -> {
            CommentVO commentVO = mapperFacade.map(comment, CommentVO.class);
            ServerResponseEntity<UserFriendCircleVO> responseEntity = userFeignClient.queryUserDetailByAccount(commentVO.getAccount());
            if (!responseEntity.isSuccess()) {
                throw new ServiceException("查询用户信息失败");
            }
            UserFriendCircleVO userFeignVO = responseEntity.getData();
            commentVO.setNickName(userFeignVO.getNickName());
            commentVO.setAvatar(userFeignVO.getPicture());
            if (commentVO.getRootCommentId() == null) {
                commentVO.setCommentCount(commentMapper.countByCommentId(commentVO.getCommentId()));
            }else {
                commentVO.setCommentCount(0);
            }
            commentVOS.add(commentVO);
        });

        return commentVOS;
    }

    @Override
    public int getCommentCount(Long friendCircleId) {
        return commentMapper.count(friendCircleId);
    }
}
