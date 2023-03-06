package com.changji.cloud.social.context;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小问号
 * @date 2023/3/6 10
 * description
 */

public class SessionContext {

    private static final Map<String, Session> map = new ConcurrentHashMap<>();

    private static String account;

    public static Map<String, Session> getMap() {
        return map;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        SessionContext.account = account;
    }

    public static void AddSession(String account, Session session) {
        if (session != null) {
            map.put(account, session);
        }
    }

    public static void DelSession(String account) {
        if (account != null) {
            map.remove(account);
        }
    }

    public static Session getSession(String account) {
        if (account == null) return null;
        return map.get(account);
    }
}
