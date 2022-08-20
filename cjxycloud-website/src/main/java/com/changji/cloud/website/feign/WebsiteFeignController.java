package com.changji.cloud.website.feign;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:20 2022/8/20
 * @ Modified By：
 */
@RestController
public class WebsiteFeignController implements WebsiteFeignClient {

    @Autowired
    private CookieService cookieService;

    @Override
    public ServerResponseEntity<AuthAccountVO> getByUserNameAndPassword(String username, String password) {

        if (username == null || password == null) {
            return ServerResponseEntity.showFailMsg("用户名或密码不能为空");
        }
        WebsiteUser websiteUser = new WebsiteUser();
        websiteUser.setAccount(username);
        websiteUser.setPassword(password);

        cookieService.getCookie(websiteUser);
        AuthAccountVO authAccountVO = new AuthAccountVO();
        String encryptPassword = SecurityUtils.encryptPassword(password);
        authAccountVO.setUsername(username);
        authAccountVO.setPassword(encryptPassword);
        return ServerResponseEntity.success(authAccountVO);
    }
}
