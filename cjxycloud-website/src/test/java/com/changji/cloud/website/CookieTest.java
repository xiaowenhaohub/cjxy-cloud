package com.changji.cloud.website;

import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.utils.CookieUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:04 2022/8/20
 * @ Modified By：
 */

@SpringBootTest
public class CookieTest {

    @Autowired
    private CookieService cookieService;

    @Test
    public void test01() {
        WebsiteUser websiteUser = new WebsiteUser();
        websiteUser.setAccount("1945829064");
        websiteUser.setPassword("(jiang.4234)");
        CookieStore cookie = cookieService.getCookie(websiteUser);
        List<Cookie> cookies = cookie.getCookies();
        cookies.forEach(e -> System.out.println(e.getValue()));
    }
}
