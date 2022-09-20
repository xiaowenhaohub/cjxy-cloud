package com.changji.cloud.social.mapper;

import com.changji.cloud.social.model.FriendCircleMessage;
import org.apache.ibatis.annotations.Param;

/**
 * @author 小问号
 * @date 2022/9/20 11
 * description
 */
public interface FriendCircleMessageMapper {

    /**
     * 保存朋友圈
     * @param friendCircleMessage
     */
    void save(@Param("friendCircleMessage") FriendCircleMessage friendCircleMessage);
}
