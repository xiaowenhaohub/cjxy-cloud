package com.changji.cloud.website.service.impl;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.service.AccountService;
import com.changji.cloud.website.utils.BufferUtil;
import com.changji.cloud.website.utils.HTMLUtil;
import com.changji.cloud.website.utils.HttpClientUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:24 2022/8/21
 * @ Modified By：
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Override
    public AuthAccountVO getAccountInfo(CookieStore cookieStore) {

        String url = HttpConstants.STUDENT_INFO.value();
        CloseableHttpResponse response = HttpClientUtils.getResponse(url, cookieStore);
        String context = BufferUtil.inputToString(response);
        AuthAccountVO authAccountVO = HTMLUtil.getStudentInfo(context);
        return authAccountVO;
    }
}
