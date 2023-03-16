package com.changji.cloud.social.controller;

import com.changji.cloud.api.user.feign.vo.UserFriendCircleVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.model.dto.FriendCircleDTO;
import com.changji.cloud.social.model.dto.UserRelationshipDTO;
import com.changji.cloud.social.model.vo.UserRelationshipVO;
import com.changji.cloud.social.service.UserRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/7 08
 * description
 */
@Api("好友管理")
@RestController
@RequestMapping("/relationship")
public class UserRelationshipController {

    @Autowired
    private UserRelationshipService userRelationshipService;

    @GetMapping("/queryAllFriend")
    @RequiresPermissions("common:social:relationship")
    @ApiOperation("查询好友列表")
    @Log(title = "查询好友列表")
    public ServerResponseEntity<List<UserRelationshipVO>> queryAllFriend() {
        List<UserRelationshipVO> userRelationshipVOS = userRelationshipService.queryAllFriends();
        return ServerResponseEntity.success("查询成功", userRelationshipVOS);
    }

    @GetMapping("/queryAllFriendRequest")
    @RequiresPermissions("common:social:relationship")
    @ApiOperation("查询好友申请列表")
    @Log(title = "查询好友申请列表")
    public ServerResponseEntity<List<UserFriendCircleVO>> queryAllFriendRequest() {
        List<UserFriendCircleVO> userRelationships = userRelationshipService.queryFriendRequest();
        return ServerResponseEntity.success("查询成功", userRelationships);

    }

    @PostMapping("/insertRelationship")
    @RequiresPermissions("common:social:relationship")
    @ApiOperation("添加好友申请")
    @Log(title = "添加好友申请")
    public ServerResponseEntity<Integer> insertRelationship(@Validated @RequestBody UserRelationshipDTO userRelationshipDTO) {
        int i = userRelationshipService.insertRelationship(userRelationshipDTO);
        return ServerResponseEntity.success("申请成功", i);
    }

    @PostMapping("/agreeFriendRequest")
    @RequiresPermissions("common:social:relationship")
    @ApiOperation("同意好友申请")
    @Log(title = "同意好友申请")
    public ServerResponseEntity<Integer> agreeFriendRequest(@Validated @RequestBody UserRelationshipDTO userRelationshipDTO) {
        int i = userRelationshipService.agreeFriendRequest(userRelationshipDTO);
        return ServerResponseEntity.success("操作成功", i);
    }

}
