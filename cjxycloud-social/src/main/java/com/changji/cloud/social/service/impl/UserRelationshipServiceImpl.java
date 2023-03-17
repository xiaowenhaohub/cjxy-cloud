package com.changji.cloud.social.service.impl;

import com.changji.cloud.api.user.feign.UserFeignClient;
import com.changji.cloud.api.user.feign.vo.UserFriendCircleVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.mapper.UserRelationshipMapper;
import com.changji.cloud.social.model.UserRelationship;
import com.changji.cloud.social.model.dto.UserRelationshipDTO;
import com.changji.cloud.social.model.vo.UserRelationshipVO;
import com.changji.cloud.social.service.UserRelationshipService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小问号
 * @date 2023/3/7 09
 * description
 */
@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public List<UserRelationshipVO> queryAllFriends() {
        String account = SecurityUtils.getAccount();
        List<UserRelationshipVO> userRelationshipVOS = userRelationshipMapper.selectFriends(account);
        userRelationshipVOS.forEach(item -> {
            ServerResponseEntity<UserFriendCircleVO> responseEntity = userFeignClient.queryUserDetailByAccount(item.getUserAccount());
            item.setAvatar(responseEntity.getData().getPicture());
        });
        return userRelationshipVOS;
    }

    @Override
    public List<UserFriendCircleVO> queryFriendRequest() {
        String account = SecurityUtils.getAccount();
        List<UserFriendCircleVO> requestList = new ArrayList();
        List<UserRelationship> userRelationships = userRelationshipMapper.queryOtherToMeFriendRequest(account);
        userRelationships.forEach(userRelationship -> {
            ServerResponseEntity<UserFriendCircleVO> responseEntity = userFeignClient.queryUserDetailByAccount(userRelationship.getUserAccount());
            if (!responseEntity.isSuccess()) {
                throw new ServiceException(responseEntity.getMsg());
            }
            requestList.add(responseEntity.getData());
        });
        return requestList;
    }

    @Override
    @Transactional
    public int insertRelationship(UserRelationshipDTO userRelationshipDTO) {
        // 查询用户是否存在
        ServerResponseEntity<UserFriendCircleVO> responseEntity = userFeignClient.queryUserDetailByAccount(userRelationshipDTO.getAccount());
        if (!responseEntity.isSuccess()) {
            throw new ServiceException(responseEntity.getMsg());
        }
        UserFriendCircleVO userFriendCircleVO = responseEntity.getData();
        if (StringUtils.isNull(userFriendCircleVO)) {
            throw new ServiceException("用户不存在");

        }

        //查询是否已经为好友
        String account = SecurityUtils.getAccount();
        UserRelationship relationship = userRelationshipMapper.selectToWayRelationship(userRelationshipDTO.getAccount(), account);
        if (StringUtils.isNotNull(relationship)) {
            throw new ServiceException("对方已经是好友");
        }
        //查询是否已经存在好友申请
        relationship = userRelationshipMapper.selectFriendRequest(userRelationshipDTO.getAccount(), account);
        if (StringUtils.isNotNull(relationship)) {
            throw new ServiceException("好友申请已存在");
        }

        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setUserAccount(account);
        userRelationship.setFriendAccount(userRelationshipDTO.getAccount());
        // 设置备注
        if (userRelationshipDTO.getAlias() != null) {
            userRelationship.setAliasUser(userRelationshipDTO.getAlias());
        }else {
            userRelationship.setAliasUser(userFriendCircleVO.getNickName());
        }

        return userRelationshipMapper.insertOneRelationShip(userRelationship);
    }

    @Override
    @Transactional
    public int agreeFriendRequest(UserRelationshipDTO userRelationshipDTO) {
        // 查询用户是否存在
        ServerResponseEntity<UserFriendCircleVO> responseEntity = userFeignClient.queryUserDetailByAccount(userRelationshipDTO.getAccount());
        if (!responseEntity.isSuccess()) {
            throw new ServiceException(responseEntity.getMsg());
        }
        UserFriendCircleVO userFriendCircleVO = responseEntity.getData();
        if (StringUtils.isNull(userFriendCircleVO)) {
            throw new ServiceException("用户不存在");

        }
        String account = SecurityUtils.getAccount();
        UserRelationship relationship = userRelationshipMapper.selectFriendRequest(userRelationshipDTO.getAccount(), account);
        if (StringUtils.isNull(relationship)) {
            throw new ServiceException("好友申请不存在");
        }

        // 设置备注
        if (userRelationshipDTO.getAlias() == null) {
            userRelationshipDTO.setAlias(userFriendCircleVO.getNickName());
        }
        return userRelationshipMapper.agreeRelationShip(userRelationshipDTO.getAccount(),account,userRelationshipDTO.getAlias());
    }
}
