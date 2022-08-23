package com.changji.cloud.common.security.interceptor;

import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.utils.IpUtils;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:40 2022/8/23
 * @ Modified By：
 */


public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (StringUtils.isNotNull(httpServletRequest)){
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);

            String authPassword = headers.get(SecurityConstants.AUTH_PASSWORD);
            if (StringUtils.isNotEmpty(authPassword)) {
                requestTemplate.header(SecurityConstants.AUTH_PASSWORD, authPassword);
            }

            // 配置客户端IP
            requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr(ServletUtils.getRequest()));
        }
    }
}
