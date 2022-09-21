package com.changji.cloud.social;

import com.changji.cloud.social.mapper.FriendCircleLikeMapper;
import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.service.LikedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FriendCircleLikeMapper friendCircleLikeMapper;

    @Test
    public void test02() {

        List<FriendCircleLike> page = friendCircleLikeMapper.findByLikedFriendCircleIdAndStatus(1L, 1);
        page.forEach(f -> System.out.println(f));

//        FriendCircleLike friendCircleLike = new FriendCircleLike();
//        friendCircleLike.setFriendCircleId(1L);
//        friendCircleLike.setUserId(2L);
//        friendCircleLike.setStatus(1);
//        List<FriendCircleLike>  list = new ArrayList<>();
//        list.add(friendCircleLike);
//        list.add(friendCircleLike);
//        int i = friendCircleLikeMapper.saveAll(list);
//        System.out.println(i);
    }

    @Autowired
    LikedService likedService;

    @Test
    public void test03() {
        likedService.transLikedCountFromRedis2DB();
    }
}
