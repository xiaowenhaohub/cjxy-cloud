package com.changji.cloud.common.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.changji.cloud.common.core.constant.SecurityConstants;
import com.changji.cloud.common.core.text.Convert;
import com.changji.cloud.common.core.utils.StringUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小问号
 * @date 2022/8/18 18
 * description  获取当前线程变量中的 用户id、用户名称、Token等信息 Thread_local 操作类
 */

public class SecurityContextHolder {

    private static  final TransmittableThreadLocal<Map<String,Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StringUtils.EMPTY : value);

    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key, StringUtils.EMPTY));
    }

    public static <T> T get(String key,Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return StringUtils.cast(map.getOrDefault(key,null));
    }

    public static Map<String,Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static Long getUserId() {
        return Convert.toLong(get(SecurityConstants.DETAILS_USER_ID), 0L);
    }

    public static void setUserId(String account) {
        set(SecurityConstants.DETAILS_USER_ID, account);
    }

    public static String getUserName() {
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    public static void setUserName(String username)
    {
        set(SecurityConstants.DETAILS_USERNAME, username);
    }

    public static String getPassword() {
        return get(SecurityConstants.AUTH_PASSWORD);
    }

    public static void setPassword(String password) {
        set(SecurityConstants.AUTH_PASSWORD, password);
    }

    public static void remove()
    {
        THREAD_LOCAL.remove();
    }
}
