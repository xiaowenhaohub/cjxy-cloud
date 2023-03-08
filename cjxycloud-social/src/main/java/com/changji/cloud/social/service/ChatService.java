package com.changji.cloud.social.service;

import com.changji.cloud.social.model.ChatMessages;
import com.changji.cloud.social.model.dto.QueryMessageDTO;
import org.springframework.stereotype.Service;
import javax.websocket.Session;
import java.util.List;

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


    /**
     * 查询聊天记录
     * @return
     */
    List<ChatMessages> getChatMessage(QueryMessageDTO queryMessageDTO);

}
