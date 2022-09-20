package com.changji.cloud.social.service;

import com.changji.cloud.social.dto.FriendCircleDTO;

/**
 * @author 小问号
 * @date 2022/9/20 14
 * description
 */
public interface FriendCircleService {

    /**
     * 保存朋友圈
     * @param friendCircleDTO
     */
    void saveFriendCircleMessage(FriendCircleDTO friendCircleDTO);
}
