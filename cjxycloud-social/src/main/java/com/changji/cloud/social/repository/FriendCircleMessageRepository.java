package com.changji.cloud.social.repository;

import com.changji.cloud.social.model.FriendCircleMessage;

/**
 * @author 小问号
 * @date 2022/9/21 13
 * description
 */
public interface FriendCircleMessageRepository {

    /**
     * 更新朋友圈信息
     * @param friendCircleMessage
     * @return
     */
    FriendCircleMessage updateFriendCircleMessage(FriendCircleMessage friendCircleMessage);

}
