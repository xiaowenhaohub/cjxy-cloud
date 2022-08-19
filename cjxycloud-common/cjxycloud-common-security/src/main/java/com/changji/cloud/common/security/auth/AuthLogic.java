package com.changji.cloud.common.security.auth;

import com.changji.cloud.common.core.exception.auth.NotLoginException;
import com.changji.cloud.common.core.exception.auth.NotPermissionException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.security.annotation.Logical;
import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 小问号
 * @date 2022/8/19 11
 * description Token 权限验证，逻辑实现类
 */

public class AuthLogic {

    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";




    /**
     * 根据注解(@RequiresPermissions)鉴定权限
     * @param permissions 注解对象
     */
    public void checkPermissionByAnnotation(RequiresPermissions permissions) {
        String[] permissionArray = permissions.value();
        if (permissions.logical() == Logical.AND) {
            checkPermissionAnd(permissionArray);
        }else {
            checkPermissionOr(permissionArray);
        }
    }

    /**
     * 验证用户是否含有指令权限，必须全部拥有
     * @param permissions
     */
    public void checkPermissionAnd(String... permissions) {
        Set<String> permissionList = getPermissionList();

        for (String permission : permissions) {
            if (!hasPermission(permissionList, permission)){
                throw new NotPermissionException(permission);
            }
        }
    }

    public void checkPermissionOr(String... permissions) {
        Set<String> permissionList = getPermissionList();
        for (String permission : permissions) {
            if (hasPermission(permissionList, permission)) {
                return;
            }
        }
        if (permissions.length > 0) {
            throw new NotPermissionException(permissions);
        }
    }


    /**
     * 获取当前用户缓存信息, 如果未登录，则抛出异常
     *
     * @return 用户缓存信息
     */
    public LoginUser getLoginUser() {
        String token = SecurityUtils.getToken();
        if (token ==null) {
            throw new NotLoginException("未提供Token");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null) {
            throw new NotLoginException("无效的Token");
        }
        return loginUser;
    }


    /**
     * 获取当前用户的权限列表
     *
     * @return 权限列表
     */
    public Set<String> getPermissionList() {
        try {
            LoginUser loginUser = getLoginUser();
            return loginUser.getPermissions();
        }catch (Exception e) {
            return new HashSet<>();
        }
    }


    /**
     * 判断用户权限列表是否包含权限
     * @param authorities 用户权限列表
     * @param permission 方法所需权限
     * @return
     */
    public boolean hasPermission(Collection<String> authorities, String permission) {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(e -> ALL_PERMISSION.contains(e) || PatternMatchUtils.simpleMatch(e, permission));
    }
}
