package com.changji.cloud.common.security.interceptor;

import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.constant.TokenConstants;
import com.changji.cloud.common.core.context.SecurityContextHolder;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.auth.AuthUtil;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

        Map<String, String> headers = ServletUtils.getHeaders(request);
        String authPassword = headers.get(SecurityConstants.AUTH_PASSWORD);

        SecurityContextHolder.setPassword(authPassword);
        //从当前线程获取 token
        String token = SecurityUtils.getToken();
        if (StringUtils.isNotEmpty(token)) {
            //从redis获取用户信息
            LoginUser loginUser = AuthUtil.getLoginUser(token);
            if (StringUtils.isNotNull(loginUser)) {
                //验证过期时间
                AuthUtil.verifyLoginUserExpire(loginUser);
                // 存入 thread_local
                SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.remove();
    }
}
