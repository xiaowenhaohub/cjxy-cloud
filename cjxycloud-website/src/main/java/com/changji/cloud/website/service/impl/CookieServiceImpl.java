package com.changji.cloud.website.service.impl;

import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.utils.CookieUtil;
import org.apache.http.client.CookieStore;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:28 2022/8/20
 * @ Modified By：
 */
@Service
public class CookieServiceImpl implements CookieService {

    @Override
    public CookieStore getCookie(WebsiteUser user) {
        return CookieUtil.getCookieStore(user);
    }
}
