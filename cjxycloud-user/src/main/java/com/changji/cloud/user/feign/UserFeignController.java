package com.changji.cloud.user.feign;

import com.changji.cloud.api.user.feign.UserFeignClient;
import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 3:03 2022/8/23
 * @ Modified By：
 */
@RestController
public class UserFeignController implements UserFeignClient {

    @Autowired
    private UserService userService;

    @Override
    public ServerResponseEntity<Object> saveUserInfo(@Validated UserDTO userDTO) {
        userService.save(userDTO);
        return ServerResponseEntity.success();
    }
}
