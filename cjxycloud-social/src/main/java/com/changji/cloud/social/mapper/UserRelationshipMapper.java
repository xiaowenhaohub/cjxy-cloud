package com.changji.cloud.social.mapper;

import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.model.vo.UserRelationshipVO;
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
    List<UserRelationshipVO> selectFriends(@Param("userAccount") String userAccount);

    /**
     * 查询是否为双向好友
     * @param userAccount
     * @param friendAccount
     * @return
     */
    UserRelationship selectToWayRelationship(@Param("userAccount") String userAccount,@Param("friendAccount") String friendAccount);

    /**
     * 查询好友申请是否存在
     * @param userAccount
     * @param friendAccount
     * @return
     */
    UserRelationship selectFriendRequest(@Param("userAccount") String userAccount,@Param("friendAccount") String friendAccount);

    /**
     * 添加一条好友申请
     * @param userRelationship
     * @return
     */
    int insertOneRelationShip(@Param("userRelationship") UserRelationship userRelationship);

    /**
     * 查询他人对自己的好友申请
     * @param userAccount
     * @return
     */
    List<UserRelationship> queryOtherToMeFriendRequest(@Param("userAccount") String userAccount);

    /**
     * 同意好友申请 friend 同意 user的申请
     * @param userAccount  申请者
     * @param friendAccount  被申请者
     * @param aliaFriend 被申请者对申请者的备注
     * @return
     */
    int agreeRelationShip(@Param("userAccount") String userAccount,@Param("friendAccount") String friendAccount,@Param("aliaFriend") String aliaFriend);

    /**
     * 修改备注
     * @param userAccount
     * @param friendAccount
     * @param alia
     * @return
     */
    int updateAlia(@Param("userAccount") String userAccount,@Param("friendAccount") String friendAccount,@Param("alia") String alia);
}
