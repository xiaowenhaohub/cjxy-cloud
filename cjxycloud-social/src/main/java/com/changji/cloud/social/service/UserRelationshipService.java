package com.changji.cloud.social.service;

import com.changji.cloud.social.mapper.UserRelationshipMapper;
import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.model.dto.UserRelationshipDTO;
import com.changji.cloud.social.model.vo.UserRelationshipVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/7 09
 * description
 */

public interface UserRelationshipService {


    /**
     * 查询好友列表
     * @return
     */
    List<UserRelationshipVO> queryAllFriends();

    /**
     * 查询好友申请
     * @return
     */
    List<UserRelationship> queryFriendRequest();

    /**
     * 添加好友申请
     * @param userRelationshipDTO
     * @return
     */
    int insertRelationship(UserRelationshipDTO userRelationshipDTO);


    /**
     * 同意好友申请
     * @param userRelationshipDTO
     * @return
     */
    int agreeFriendRequest(UserRelationshipDTO userRelationshipDTO);


}
