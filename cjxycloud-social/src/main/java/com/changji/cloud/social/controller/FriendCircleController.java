package com.changji.cloud.social.controller;
import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.social.model.dto.FriendCircleDTO;
import com.changji.cloud.social.service.FriendCircleService;
import com.changji.cloud.social.model.vo.FriendCircleMessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/social/deleteFriendCircle/{friendCircleId}")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("删除朋友圈")
    @Log(title = "删除朋友圈")
    public ServerResponseEntity<Void> deleteFriendCircleMessage(@PathVariable("friendCircleId") Long friendCircleId) {
        friendCircleService.deleteFriendCircleMessage(friendCircleId);
        return ServerResponseEntity.success();
    }

    @PostMapping("/social/getFriendCircle/{userId}")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("获取朋友圈")
    @Log(title = "获取朋友圈")
    public ServerResponseEntity<List<FriendCircleMessageVO>> getFriendCircleMessageMapper(@PathVariable("userId") Long userId, @Validated @RequestBody Page page){
        List<FriendCircleMessageVO> friendCircleMessageList = friendCircleService.getFriendCircleList(userId, page);
        return ServerResponseEntity.success(friendCircleMessageList);
    }

    @GetMapping("/social/likedFriendCircle/{friendCircleId}")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("点赞朋友圈")
    @Log(title = "点赞朋友圈")
    public ServerResponseEntity<Void> likedFriendCircle(@PathVariable("friendCircleId") Long friendCircleId) {
        friendCircleService.likedFriendCircle(friendCircleId);
        return ServerResponseEntity.success();
    }

    @PostMapping("/moments/get")
    @RequiresPermissions("common:social:friendCircle")
    @ApiOperation("获取校园圈")
    @Log(title = "获取校园圈")
    public ServerResponseEntity<List<FriendCircleMessageVO>> getMoments(@RequestBody Page page) {
        List<FriendCircleMessageVO> moments = friendCircleService.getMoments(page);
        return ServerResponseEntity.success("查询成功",moments);
    }
}
