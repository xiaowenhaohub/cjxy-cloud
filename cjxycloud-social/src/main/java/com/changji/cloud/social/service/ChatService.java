package com.changji.cloud.social.service;

import com.changji.cloud.social.model.ChatMessages;
import org.springframework.stereotype.Service;
import javax.websocket.Session;

/**
 * @author 小问号
 * @date 2023/3/6 09
 * description
 */
public interface ChatService {

    /**
     * 发送信息
     */
    void sendMessageById(ChatMessages chatMessages);



}
