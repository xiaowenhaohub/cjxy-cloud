package com.changji.cloud.social.repository.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.repository.FriendCircleMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 小问号
 * @date 2022/9/21 13
 * description
 */

@Repository
public class FriendCircleMessageRepositoryImpl implements FriendCircleMessageRepository {

    @Autowired
    private FriendCircleMessageMapper friendCircleMessageMapper;

    @Override
    public FriendCircleMessage updateFriendCircleMessage(FriendCircleMessage friendCircleMessage) {

        int i = friendCircleMessageMapper.updateFriendCircleMessage(friendCircleMessage);
        if (i != 1) {
            throw new ServiceException("更新朋友圈失败");
        }
        return friendCircleMessage;
    }
}
