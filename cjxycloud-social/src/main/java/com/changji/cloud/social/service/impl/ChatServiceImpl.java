package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.mapper.ChatMessagesMapper;
import com.changji.cloud.social.mapper.UserRelationshipMapper;
import com.changji.cloud.social.model.ChatMessages;
import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.model.dto.QueryMessageDTO;
import com.changji.cloud.social.service.ChatService;
import com.changji.cloud.social.utils.ChatUtils;
import com.changji.cloud.social.utils.Result;
import com.changji.cloud.social.utils.SessionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
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
    public void saveSession(Session session, String account) {
        SessionUtils.saveSession(session, account);
        log.info("用户连接:{}",SessionUtils.getAccount());
        Result.sendLocal(200,"连接成功", "null");
    }

    @Override
    public void sendMessageById(ChatMessages chatMessages) {

        String account = SessionUtils.getAccount();
        log.info(account + "发送消息给:"+chatMessages.getToUserAccount());

        // 验证是否为好友
        UserRelationship userRelationship = userRelationshipMapper.selectToWayRelationship(account, chatMessages.getToUserAccount());
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

    @Override
    public void heartPacket(ChatMessages chatMessages) {
        Result.send(chatMessages.getCode(), chatMessages.getContent(),null, SessionUtils.getAccount(),SessionUtils.getAccount());
        log.info("心跳检测{}",SessionUtils.getAccount());
    }

    @Override
    public List<ChatMessages> getChatMessage(QueryMessageDTO queryMessageDTO) {
        if(StringUtils.isEmpty(queryMessageDTO.getAccount())) {
            throw new ServiceException("对方学号不能为空");
        }

        String account = SecurityUtils.getAccount();
        if(queryMessageDTO.getStartIndex() == null || queryMessageDTO.getPageSize() == null) {
            queryMessageDTO.setPageSize(14);
            queryMessageDTO.setStartIndex(0);
        }

//        int count = chatMessagesMapper.count(account,queryMessageDTO.getAccount());
//        int num = count - queryMessageDTO.getStartIndex();
//        if (num >= queryMessageDTO.getPageSize()){
//            int startIndex = count - queryMessageDTO.getStartIndex() -queryMessageDTO.getPageSize();
//            queryMessageDTO.setStartIndex(startIndex);
//        }else if (num < queryMessageDTO.getPageSize() && num >= 0) {
//            queryMessageDTO.setStartIndex(0);
//            queryMessageDTO.setPageSize(num);
//        }else{
//            return null;
//        }
//        PageHelper.startPage(queryMessageDTO.getStartIndex(),queryMessageDTO.getPageSize());
//        PageInfo<ChatMessages> pageInfo = new PageInfo<>(chatMessagesMapper.selectMessageByFromAndToAccount(account,queryMessageDTO.getAccount()));
        List<ChatMessages> chatMessages = chatMessagesMapper.selectMessageByFromAndToAccount(account, queryMessageDTO.getAccount(), queryMessageDTO.getStartIndex(), queryMessageDTO.getPageSize());

        return  chatMessages;
    }



}
