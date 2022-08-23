package com.changji.cloud.website.feign;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.AccountService;
import com.changji.cloud.website.service.CookieService;
import org.apache.http.client.CookieStore;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountService accountService;

    @Override
    public ServerResponseEntity<AuthAccountVO> getByAccountAndPassword(String account, String password) {

        if (account == null || password == null) {
            return ServerResponseEntity.showFailMsg("用户名或密码不能为空");
        }
        WebsiteUser user = new WebsiteUser();
        user.setAccount(account);
        user.setPassword(password);

        CookieStore cookieStore = cookieService.getCookie(user);

        /**
         * 从官网获取学生详情
         */
        AuthAccountVO authAccountVO = accountService.getAccountInfo(cookieStore);
        String encryptPassword = SecurityUtils.encryptPassword(password);
        authAccountVO.setAccount(account);
        authAccountVO.setPassword(encryptPassword);
        return ServerResponseEntity.success(authAccountVO);
    }

    @Override
    public ServerResponseEntity<Object> testFallback(String username) {

        System.out.println(username);

        if("1".equals(username)) {
            System.out.println("抛出异常");
            throw new ServiceException("fall back test");
        }

        return ServerResponseEntity.success(username);
    }
}
