package com.changji.cloud.common.security.interceptor;

import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.constant.TokenConstants;
import com.changji.cloud.common.core.context.SecurityContextHolder;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.security.utils.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小问号
 * @date 2022/8/19 14
 * description
 */

public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));
        String token = SecurityUtils.getToken();
//        if (StringUtils.isNotEmpty(token)) {
//
//        }

        System.out.println(ServletUtils.getHeader(request, TokenConstants.AUTHENTICATION));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.remove();
    }
}
