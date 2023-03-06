package com.changji.cloud.social.utils;

import com.alibaba.fastjson2.JSON;
import com.changji.cloud.social.model.ChatMessages;

/**
 * @author 小问号
 * @date 2023/3/6 09
 * description
 */

public class ChatUtils {

    public static ChatMessages parseMessage(String message) {
        return JSON.parseObject(message, ChatMessages.class);
    }

    public static void main(String[] args) {
        String jsonString = "{\"fromUserAccount\":\"111\",\"toUserAccount\":\"222\",\"content\":25}";
        String message = "{fromUserAccount: '111', toUserAccount:'222', content:'test'}";
        System.out.println(parseMessage(jsonString));
    }


}
