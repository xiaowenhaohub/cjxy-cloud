package com.changji.cloud.social.controller;

import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.context.SecurityContextHolder;
import com.changji.cloud.common.core.utils.SpringUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.auth.AuthUtil;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.social.service.ChatService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/chat/{token}")
@Component
@Slf4j
public class WebSocketController {

    @Autowired
    private ChatService chatService;

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token, EndpointConfig config){
        if (StringUtils.isEmpty(token)) {
            return;
        }
        //从redis获取用户信息
        LoginUser loginUser = AuthUtil.getLoginUser(token);
        if (StringUtils.isNull(loginUser)) {
            log.info("websocket用户未登录");
           return;
        }
        //验证过期时间
        AuthUtil.verifyLoginUserExpire(loginUser);
        // 存入 thread_local
        SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
        if(chatService == null){
            this.chatService = SpringUtils.getBean(ChatService.class);
        }
        chatService.addUserToSessionMap(session);
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        chatService.sendMessageById(message);
    }

    @OnClose
    public void onClose() {
       chatService.onClose();

    }

    @OnError
    public void onError(Throwable throwable) {
        chatService.onError(throwable);
    }

}
