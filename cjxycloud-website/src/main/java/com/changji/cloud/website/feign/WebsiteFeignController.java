package com.changji.cloud.website.feign;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:20 2022/8/20
 * @ Modified By：
 */
public class WebsiteFeignController implements WebsiteFeignClient {

    @Autowired
    private CookieService cookieService;

    @Override
    public ServerResponseEntity<AuthAccountVO> getByUserNameAndPassword(String username, String password) {

        WebsiteUser websiteUser = new WebsiteUser();
        websiteUser.setAccount(username);
        websiteUser.setAccount(password);

        return null;
    }
}
