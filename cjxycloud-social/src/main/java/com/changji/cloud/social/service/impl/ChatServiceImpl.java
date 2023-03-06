package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.model.ChatMessages;
import com.changji.cloud.social.service.ChatService;
import com.changji.cloud.social.utils.ChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小问号
 * @date 2023/3/6 09
 * description
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private String account;

    @Override
    public void sendMessageById(String message) {
        ChatMessages chatMessages = ChatUtils.parseMessage(message);
        chatMessages.setFromUserAccount(account);
        log.info("发送消息给:"+chatMessages.getToUserAccount());
        Session session = sessionMap.get(chatMessages.getToUserAccount());
        if (sessionMap.containsKey(chatMessages.getToUserAccount())) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }
    @Override
    public void addUserToSessionMap(Session session) {
        this.account = SecurityUtils.getLoginUser().getAccount();
        log.info("用户连接:"+account);
        if(!sessionMap.containsKey(account)){
            sessionMap.put(account, session);
        }
    }

    @Override
    public void onClose() {
        sessionMap.remove(account);
        log.info("用户" + account + "断开连接");
    }

    @Override
    public void onError(Throwable throwable) {
        sessionMap.remove(account);
        log.info("用户:" +account+ "断开连接");
        log.info("发生错误:",throwable);
    }
}
