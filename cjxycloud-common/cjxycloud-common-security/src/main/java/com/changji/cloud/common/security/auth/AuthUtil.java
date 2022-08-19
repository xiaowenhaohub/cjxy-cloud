package com.changji.cloud.common.security.auth;

import com.changji.cloud.common.security.annotation.RequiresPermissions;

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

}
