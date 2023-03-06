package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.mapper.ChatMessagesMapper;
import com.changji.cloud.social.mapper.UserRelationshipMapper;
import com.changji.cloud.social.model.ChatMessages;
import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.service.ChatService;
import com.changji.cloud.social.utils.ChatUtils;
import com.changji.cloud.social.utils.Result;
import com.changji.cloud.social.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private ChatMessagesMapper chatMessagesMapper;

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void sendMessageById(ChatMessages chatMessages) {
        log.info("发送消息给:"+chatMessages.getToUserAccount());
        String account = SessionUtils.getAccount();

        // 验证是否为好友
        UserRelationship userRelationship = userRelationshipMapper.selectRelationship(account, chatMessages.getToUserAccount());
        if (StringUtils.isNull(userRelationship)) {
            log.error("不是好友关系");
            Result.sendLocal(4040,"不是好友关系",null);
            return;
        }
        chatMessages.setFromUserAccount(account);
        // 保存聊天信息
        chatMessagesMapper.insertMessage(chatMessages);
        Result.success("发送消息",chatMessages.getContent(),chatMessages.getToUserAccount());
    }


}