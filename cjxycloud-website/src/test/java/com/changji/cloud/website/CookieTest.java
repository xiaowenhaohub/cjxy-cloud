package com.changji.cloud.website;

import com.changji.cloud.common.core.utils.DateUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.mapper.CoursesMapper;
import com.changji.cloud.website.model.Courses;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.utils.CookieUtil;
import ma.glasnost.orika.MapperFacade;
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

    @Test
    public void test03() {

        Integer year = DateUtils.getCurrentYear();
        Integer month = DateUtils.getCurrentMonth();


        String currentYearSemester = DateUtils.getCurrentYearSemester(DateUtils.getCurrentYear(), DateUtils.getCurrentMonth(), DateUtils.SEMESTER);
        System.out.println("year:" + year);
        System.out.println("month:" + month);
    }

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
