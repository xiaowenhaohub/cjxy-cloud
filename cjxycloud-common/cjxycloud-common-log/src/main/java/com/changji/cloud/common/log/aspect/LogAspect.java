package com.changji.cloud.common.log.aspect;

import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.log.annotation.Log;
import com.changji.cloud.common.log.model.OperationLog;
import com.changji.cloud.common.log.service.LogService;
import com.changji.cloud.common.security.model.LoginUser;
import com.changji.cloud.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Servlet;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:48 2022/8/25
 * @ Modified By：
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */

    @AfterReturning(pointcut ="@annotation(controllerLog)",returning = "serverResponseEntity")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object serverResponseEntity) {
        handleLog(joinPoint, controllerLog, null, serverResponseEntity);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object serverResponseEntity){
        try {
            OperationLog operationLog = new OperationLog();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operationLog.setMethod(className + "." + methodName + "()");
            operationLog.setOperationUrl(ServletUtils.getRequest().getRequestURI());
            getControllerMethodDescription(joinPoint,controllerLog,operationLog,serverResponseEntity);

            if (e != null)
            {
//                operationLog.setStatus(BusinessStatus.FAIL.ordinal());
                operationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (loginUser != null) {
                operationLog.setAccount(loginUser.getAccount());
            }

            logService.printLog(operationLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }

    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param controllerLog 日志
     * @param operationLog 操作日志
     * @throws Exception
     */
    protected void getControllerMethodDescription(JoinPoint joinPoint, Log controllerLog, OperationLog operationLog, Object serverResponseEntity) {
        operationLog.setTitle(controllerLog.title());
    }

}
