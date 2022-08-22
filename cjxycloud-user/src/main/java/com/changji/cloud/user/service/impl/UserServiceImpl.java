package com.changji.cloud.user.service.impl;

import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.user.mapper.UsersMapper;
import com.changji.cloud.user.model.User;
import com.changji.cloud.user.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void save(UserDTO userDTO) {
        User user = mapperFacade.map(userDTO, User.class);

        usersMapper.save(user);
    }
}
