package com.changji.cloud.website.service.impl;

import com.changji.cloud.common.core.exception.NullParamException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.auth.AuthUtil;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.dto.QueryCourseDTO;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.service.CourseService;
import com.changji.cloud.website.utils.BufferUtil;
import com.changji.cloud.website.utils.HttpClientUtils;
import org.apache.catalina.security.SecurityUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:04 2022/8/20
 * @ Modified By：
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CookieService cookieService;

    @Override
    public void getMyCourseList(QueryCourseDTO queryCourseDTO) {

        LoginUser loginUser = AuthUtil.getLoginUser();
        WebsiteUser user = new WebsiteUser();
        user.setAccount(loginUser.getUsername());
        user.setPassword(SecurityUtils.getPassword());
        CookieStore cookieStore = cookieService.getCookie(user);


        String url = HttpConstants.MY_COURSE_URL.value();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("cj0701id", ""));
        list.add(new BasicNameValuePair("zc",queryCourseDTO.getWeekly()));//周次
        list.add(new BasicNameValuePair("demo",""));
        list.add(new BasicNameValuePair("xnxq01id",queryCourseDTO.getSemester()));//学年学期
        list.add(new BasicNameValuePair("sfFD",""));
        list.add(new BasicNameValuePair("wkbkc","1"));//无课表课程
        list.add(new BasicNameValuePair("kbjcmsid","A63DB2E690D94F43945978F87DBE514D"));//默认节次模式

        HttpPost httpPost = HttpClientUtils.getHttpPost(list, url);

        CloseableHttpClient httpClient = HttpClientUtils.createSSLClientCustom(cookieStore);

        CloseableHttpResponse response = HttpClientUtils.getResponse(httpClient, httpPost);

        String context = BufferUtil.inputToString(response);

        System.out.println(context);

    }
}
