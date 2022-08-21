package com.changji.cloud.common.security.auth;

import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.common.security.model.LoginUser;

/**
 * @author 小问号
 * @date 2022/8/19 14
 * description Token 权限验证工具类
 */

public class AuthUtil {

    private static  AuthLogic authlogic = new AuthLogic();

    /**
     * 根据注解传入参数鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     * @param requiresPermissions 方法的注解对象
     */
    public static void checkPermissions(RequiresPermissions requiresPermissions) {
        authlogic.checkPermissionByAnnotation(requiresPermissions);
    }

    /**
     * 验证当前用户有效期
     */
    public static void verifyLoginUserExpire(LoginUser loginUser) {
        authlogic.verifyLoginUserExpire(loginUser);
    }


    /**
     * 根据token 从redis获取loginuser
     * @param token
     * @return
     */
    public static LoginUser getLoginUser(String token) {
        return authlogic.getLoginUser(token);
    }

}
