package com.changji.cloud.social.controller;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.social.dto.FriendCircleDTO;
import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.service.FriendCircleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */
@Api("朋友圈")
@RestController
public class FriendCircleController {

    @Autowired
    private FriendCircleService friendCircleService;

    @PostMapping("/social/saveFriendCircle")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("保存朋友圈")
    @Log(title = "保存朋友圈")
    public ServerResponseEntity<Void> saveFriendCircleMessage(@Validated @RequestBody FriendCircleDTO friendCircleDTO) {
        friendCircleService.saveFriendCircleMessage(friendCircleDTO);
        return ServerResponseEntity.success();
    }

    @PostMapping("/social/getFriendCircle")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("获取朋友圈")
    @Log(title = "获取朋友圈")
    public ServerResponseEntity<List<FriendCircleMessage>> getFriendCircleMessageMapper(@Validated @RequestBody Page page){
        List<FriendCircleMessage> friendCircleMessageList = friendCircleService.getFriendCircleList(page);

        return ServerResponseEntity.success(friendCircleMessageList);
    }
}
