package com.changji.cloud.social;

import com.changji.cloud.social.mapper.FriendCircleCommentMapper;
import com.changji.cloud.social.model.FriendCircleComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 小问号
 * @date 2023/3/17 16
 * description
 */
@SpringBootTest
public class CommentTest {
    @Autowired
    private FriendCircleCommentMapper friendCircleCommentMapper;

    @Test
    public void test05() {
        FriendCircleComment friendCircleComment = new FriendCircleComment();
        friendCircleComment.setFriendCircleId(12L);
//        friendCircleComment.setUserId(2L);
        friendCircleComment.setContent("test 9/23 11:02");
        friendCircleCommentMapper.save(friendCircleComment);
    }
}
