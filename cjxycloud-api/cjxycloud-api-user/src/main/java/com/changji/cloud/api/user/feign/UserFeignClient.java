package com.changji.cloud.api.user.feign;

import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.api.user.feign.vo.UserFriendCircleVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 2:55 2022/8/23
 * @ Modified By：
 */

@FeignClient(value = "cjxycloud-user",contextId = "userService")
public interface UserFeignClient {

    @PostMapping(value =  "/feign/user/save")
    public ServerResponseEntity<Object> saveUserInfo(@RequestBody UserDTO userDTO);

    @PostMapping("feign/user/queryUserDetailById")
    public ServerResponseEntity<UserFriendCircleVO> queryUserDetailById(@RequestBody Long userId);

    @GetMapping("feign/user/queryUserDetailById/{userAccount}")
    public ServerResponseEntity<UserFriendCircleVO> queryUserDetailByAccount(@PathVariable("userAccount") String userAccount);

}
