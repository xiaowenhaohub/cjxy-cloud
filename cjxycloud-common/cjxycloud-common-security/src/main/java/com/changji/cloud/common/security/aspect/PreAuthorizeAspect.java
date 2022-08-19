package com.changji.cloud.common.security.aspect;

import com.changji.cloud.common.security.annotation.RequiresPermissions;
import com.changji.cloud.common.security.auth.AuthUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:17 2022/8/19
 * @ Modified By：
 */
@Component
@Aspect
public class PreAuthorizeAspect {

    public PreAuthorizeAspect() {

    }

    /**
     * 定义AOP签名 (切入所有使用鉴权注解的方法)
     */
    public static final String POINTCUT_SIGN = "@annotation(com.changji.cloud.common.security.annotation.RequiresPermissions)";


    /**
     * 声明AOP签名
     */
    @Pointcut(POINTCUT_SIGN)
    public void pointcut() {
    }


    /**
     * 环绕切入
     *
     * @param joinPoint 切面对象
     * @return 底层方法执行后的返回值
     * @throws Throwable 底层方法抛出的异常
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        }
        catch (Throwable e){
            throw e;
        }
    }


    /**
     * 检查方法的注解 根据权限注解检查用户权限
     * @param method
     */
    public void checkMethodAnnotation(Method method) {

        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            AuthUtil.checkPermissions(requiresPermissions);
        }

    }
}
