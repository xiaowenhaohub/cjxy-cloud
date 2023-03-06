package com.changji.cloud.social.service;

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
     * @param message
     */
    void sendMessageById(String message);

    /**
     * 用户连接
     * @param session
     */
    void addUserToSessionMap(Session session);

    /**
     * 断开连接
     */
    void onClose();

    /**
     * 错误
     */
    void onError(Throwable throwable);
}
