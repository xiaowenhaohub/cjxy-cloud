package com.changji.cloud.social.utils;

import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.context.SessionContext;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @author 小问号
 * @date 2023/3/6 14
 * description
 */

public class SessionUtils {

    /**
     * 获取用户学号
     * @return
     */
    public static String getAccount() {
        return SessionContext.getAccount();
    }

    /**
     * 获取Session
     * @param account
     * @return
     */
    public static Session getSession(String account) {
        return SessionContext.getSession(account);
    }

    /**
     * 获取当前用户session
     * @return
     */
    public static Session getLocalSession(){
        return getSession(getAccount());
    }


    /**
     * 保存当前用户Session
     */
    public static void saveSession(Session session) {
        SessionContext.setAccount(SecurityUtils.getAccount());
        SessionContext.AddSession(getAccount(),session);
    }

    /**
     * 删除当前用户Session
     */
    public static void removeLocal(){
        SessionContext.DelSession(getAccount());
    }

    /**
     * 删除Session
     * @param account
     */
    public static void remove(String account){
        SessionContext.DelSession(account);
    }

    /**
     * 发送消息
     * @param message
     * @param toAccount
     * @return
     */
    public static boolean sendMessage(String message, String toAccount) {
            try {
                Session session = getSession(toAccount);
                if (session == null) {
                    return false;
                }
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                return false;
        }
        return true;
    }

}
