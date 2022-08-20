package com.changji.cloud.api.website.feign;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:10 2022/8/20
 * @ Modified By：
 */

@FeignClient(value = "cjxycloud-website",contextId = "websiteService")
public interface WebsiteFeignClient {

    /**
     * 根据用户名和密码从教务管理系统验证用户，验证失败返回null
     * @return
     */
    @PostMapping(value =  "/feign/website/getByUserNameAndPassword")
    ServerResponseEntity<AuthAccountVO> getByUserNameAndPassword(@RequestParam("username") String username,@RequestParam("password") String password);

}
