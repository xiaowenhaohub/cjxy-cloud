package com.changji.cloud.user.feign;

import com.changji.cloud.api.user.feign.UserFeignClient;
import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.api.user.feign.vo.UserFriendCircleVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.user.model.User;
import com.changji.cloud.user.service.UserService;
import com.changji.cloud.user.vo.UserVO;
import ma.glasnost.orika.MapperFacade;
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

    @Autowired
    private MapperFacade mapperFacade;



    @Override
    public ServerResponseEntity<Object> saveUserInfo(@Validated UserDTO userDTO) {

        userService.save(userDTO);
        return ServerResponseEntity.success();
    }

    @Override
    public ServerResponseEntity<UserFriendCircleVO> queryUserDetailById(Long userId) {
        User user = userService.queryUserById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return ServerResponseEntity.success(new UserFriendCircleVO(user.getNickName(), user.getPicture()));
    }

    @Override
    public ServerResponseEntity<UserFriendCircleVO> queryUserDetailByAccount(String userAccount) {
        UserVO userVO = userService.queryUserByAccount(userAccount);
        UserFriendCircleVO userFriendCircleVO = mapperFacade.map(userVO, UserFriendCircleVO.class);
        return ServerResponseEntity.success(userFriendCircleVO);
    }
}
