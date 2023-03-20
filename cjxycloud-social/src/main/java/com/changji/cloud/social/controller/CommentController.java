package com.changji.cloud.social.controller;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.social.model.FriendCircleComment;
import com.changji.cloud.social.model.dto.CommentDTO;
import com.changji.cloud.social.model.dto.GetCommentDTO;
import com.changji.cloud.social.model.vo.CommentVO;
import com.changji.cloud.social.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/17 16
 * description
 */
@Api("评论")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("评论")
    @Log(title = "评论")
    public ServerResponseEntity<Boolean> save(@Validated @RequestBody CommentDTO commentDTO) {
        return ServerResponseEntity.success(commentService.save(commentDTO));
    }


    @PostMapping("/get")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("获取评论")
    @Log(title = "获取评论")
    public ServerResponseEntity<List<CommentVO>> getComment(@Validated @RequestBody GetCommentDTO commentDTO) {
        System.out.println(commentDTO);
        List<CommentVO> commentList = commentService.getCommentList(commentDTO, commentDTO);
        return ServerResponseEntity.success(commentList);
    }

}
