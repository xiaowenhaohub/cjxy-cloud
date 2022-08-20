package com.changji.cloud.website.service.impl;

import com.changji.cloud.common.core.exception.NullParamException;
import com.changji.cloud.website.constant.HttpConstants;
import com.changji.cloud.website.model.WebsiteUser;
import com.changji.cloud.website.service.CookieService;
import com.changji.cloud.website.utils.CookieUtil;
import org.apache.http.client.CookieStore;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:28 2022/8/20
 * @ Modified By：
 */
@Service
public class CookieServiceImpl implements CookieService {

    @Override
    public CookieStore getCookie(WebsiteUser user) {

        if (Objects.isNull(user)) {
            throw new NullParamException("获取cookie user不能为空");
        }

        return CookieUtil.getCookieStore(user, HttpConstants.LOGIN_URL.value());
    }
}
