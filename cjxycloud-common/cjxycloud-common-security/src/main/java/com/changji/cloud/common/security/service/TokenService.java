package com.changji.cloud.common.security.service;

import com.changji.cloud.common.core.constant.CacheConstants;
import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.utils.IpUtils;
import com.changji.cloud.common.core.utils.JwtUtils;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.core.utils.uuid.IdUtils;
import com.changji.cloud.common.redis.service.RedisService;
import com.changji.cloud.common.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:13 2022/8/21
 * @ Modified By：
 */
@Service
public class TokenService {

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    /**
     * 240分钟
     */
    private final static Long MILLIS_MINUTE_TEN = 240 * MILLIS_MINUTE;

    /**
     * 超时时间 7天
     */
    private final static long expireTime = CacheConstants.EXPIRATION;

    /**
     * token key 前缀
     */
    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    @Autowired
    private RedisService redisService;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);
        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.USER_KEY, token);

        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getLoginUser(request);
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = ServletUtils.getToken(request);
        return getLoginUser(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token){
        LoginUser loginUser = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String userKey = JwtUtils.getUserKey(token);
                loginUser = redisService.getCacheObject(getTokenKey(userKey));
            }
        }catch (Exception e) {

        }
        return loginUser;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userkey = JwtUtils.getUserKey(token);
            redisService.deleteObject(getTokenKey(userkey));
        }
    }

    /**
     * 验证令牌有效期，相差不足240分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     * 存入redis
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        String tokenKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(tokenKey, loginUser, expireTime, TimeUnit.MINUTES);

    }

    public String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }

}
