package com.changji.cloud.social.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */
@Api("朋友圈")
@RestController
public class FriendCircleController {

    @PostMapping("/social/saveFriendCircle")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("保存朋友圈")
    @Log(title = "保存朋友圈")
    public ServerResponseEntity<?> saveFriendCircleMessage() {

        return ServerResponseEntity.success();
    }
}
