package com.changji.cloud.website.service;

import com.changji.cloud.website.model.WebsiteUser;
import org.apache.http.client.CookieStore;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:27 2022/8/20
 * @ Modified By：
 */

public interface CookieService {
    public CookieStore getCookie(WebsiteUser user);
}
