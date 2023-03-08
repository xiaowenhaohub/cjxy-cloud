package com.changji.cloud.social.controller;

import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.social.model.ChatMessages;
import com.changji.cloud.social.model.dto.QueryMessageDTO;
import com.changji.cloud.social.model.vo.UserRelationshipVO;
import com.changji.cloud.social.service.ChatService;
import com.changji.cloud.social.service.UserRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/8 15
 * description
 */
@Api("聊天记录管理")
@RestController
@RequestMapping("/message")
public class ChatMessageController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/get")
    @RequiresPermissions("common:social:relationship")
    @ApiOperation("查询聊天记录")
    @Log(title = "查询聊天记录")
    public ServerResponseEntity<List<ChatMessages>> getAllMessage(@RequestBody QueryMessageDTO queryMessageDTO) {
        List<ChatMessages> chatMessage = chatService.getChatMessage(queryMessageDTO);
        return ServerResponseEntity.success("查询成功", chatMessage);
    }
}
