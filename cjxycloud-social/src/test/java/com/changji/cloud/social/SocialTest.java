package com.changji.cloud.social;

import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */

@SpringBootTest
public class SocialTest {

    @Autowired
    private FriendCircleMessageMapper friendCircleMessageMapper;

    @Test
    public void test01() {
        FriendCircleMessage friendCircleMessage = new FriendCircleMessage();

        friendCircleMessage.setUserId(222L);
        friendCircleMessage.setContent("xiaowenhao");
        friendCircleMessageMapper.save(friendCircleMessage);
    }
}
