package com.changji.cloud.user.service.impl;

import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.user.mapper.UsersMapper;
import com.changji.cloud.user.model.User;
import com.changji.cloud.user.service.UserService;
import com.changji.cloud.user.vo.UserVO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 3:05 2022/8/23
 * @ Modified By：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        User user = mapperFacade.map(userDTO, User.class);
        usersMapper.save(user);
    }

    @Override
    public UserVO queryUserByAccount(String account) {
        User user = usersMapper.queryUserByAccount(account);
        return mapperFacade.map(user, UserVO.class);
    }

    @Override
    public UserVO queryMeDetails() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = usersMapper.queryUserByAccount(loginUser.getAccount());
        UserVO userVO = mapperFacade.map(user, UserVO.class);
        return userVO;
    }


    @Override
    public User queryUserById(Long userId) {
        return usersMapper.queryUserById(userId);
    }
}
