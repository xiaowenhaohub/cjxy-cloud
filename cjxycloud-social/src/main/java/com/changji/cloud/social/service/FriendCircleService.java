package com.changji.cloud.social.service;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.social.dto.FriendCircleDTO;
import com.changji.cloud.social.model.FriendCircleMessage;

import java.util.List;

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


    /**
     * 获取朋友圈
     * @param page
     * @return
     */
    List<FriendCircleMessage> getFriendCircleList(Page page);
}
