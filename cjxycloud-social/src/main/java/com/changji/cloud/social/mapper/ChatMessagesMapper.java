package com.changji.cloud.social.mapper;

import com.changji.cloud.social.model.ChatMessages;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/6 13
 * description
 */

@Repository
public interface ChatMessagesMapper {

    /**
     * 保存消息
     * @param chatMessages
     * @return
     */
    int insertMessage(@Param("chatMessages")  ChatMessages chatMessages);

    /**
     * 查询聊天记录
     * @param fromAccount
     * @param toAccount
     * @return
     */
    List<ChatMessages> selectMessageByFromAndToAccount(@Param("fromAccount") String fromAccount,@Param("toAccount") String toAccount);

    /**
     * 总数
     * @return
     */
    int count(@Param("fromAccount") String fromAccount,@Param("toAccount") String toAccount);
}
