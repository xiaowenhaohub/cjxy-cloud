package com.changji.cloud.social.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小问号
 * @date 2023/3/6 10
 * description
 */

public class SessionContext {
    private static SessionContext instance;
    private Map<String, HttpSession> map;
    private SessionContext() {
        map = new ConcurrentHashMap<>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    synchronized void AddSession(HttpSession session) {
        if (session != null) {
            map.put(session.getId(), session);
        }
    }

    synchronized void DelSession(HttpSession session) {
        if (session != null) {
            map.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return map.get(session_id);
    }
}
