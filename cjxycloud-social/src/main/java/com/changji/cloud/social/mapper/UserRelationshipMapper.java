package com.changji.cloud.social.mapper;

import com.changji.cloud.social.model.UserRelationship;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/6 13
 * description
 */
@Repository
public interface UserRelationshipMapper {

    /**
     * 查询好友列表
     * @param userAccount
     * @return
     */
    List<UserRelationship> selectFriends(@Param("userAccount") String userAccount);

    /**
     * 查询是否为双向好友
     * @param userAccount
     * @param friendAccount
     * @return
     */
    UserRelationship selectRelationship(@Param("userAccount") String userAccount,@Param("friendAccount") String friendAccount);
}
