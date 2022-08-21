package com.changji.cloud.website.service.impl;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.security.auth.AuthUtil;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.model.StudentInfo;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.service.StudentService;
import com.changji.cloud.website.utils.BufferUtil;
import com.changji.cloud.website.utils.HTMLUtil;
import com.changji.cloud.website.utils.HttpClientUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:24 2022/8/21
 * @ Modified By：
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Override
    public AuthAccountVO getStudentInfo(CookieStore cookieStore) {

        String url = HttpConstants.STUDENT_INFO.value();
        CloseableHttpResponse response = HttpClientUtils.getResponse(url, cookieStore);
        String context = BufferUtil.inputToString(response);
        AuthAccountVO authAccountVO = HTMLUtil.getStudentInfo(context);
        return authAccountVO;
    }
}
