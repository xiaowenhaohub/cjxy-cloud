package com.changji.cloud.common.security.handler;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.exception.auth.NotLoginException;
import com.changji.cloud.common.core.exception.auth.NotPermissionException;
import com.changji.cloud.common.core.exception.auth.NotRoleException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 小问号
 * @date 2022/8/19 14
 * description 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * token异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(NotLoginException.class)
    public <T> ServerResponseEntity<T> handleNotLoginException(NotLoginException e) {
        String message = e.getMessage();
        log.error(message);
        return ServerResponseEntity.showFailMsg(message);
    }

    /**
     * 处理参数校验异常 --Json 转换异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public <T> ServerResponseEntity<T> exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("参数校验异常-json转换异常:", e);
        return ServerResponseEntity.showFailMsg("参数校验异常-json转换异常:" + req.getRequestURI());
    }


    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public <T> ServerResponseEntity<T>  handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return ServerResponseEntity.showFailMsg("没有访问权限，请联系管理员授权");
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public <T> ServerResponseEntity<T>  handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
        return ServerResponseEntity.showFailMsg("没有访问权限，请联系管理员授权");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> ServerResponseEntity<T>  handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, message);
        return ServerResponseEntity.showFailMsg(message);
    }


    /**
     * 业务异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public <T> ServerResponseEntity<T> handlerServiceException(ServiceException e, HttpServletRequest request) {
        String message = e.getMessage();
        log.error(message);
        return ServerResponseEntity.showFailMsg(message);
    }

    /**
     * 拦截未知的运行异常
     */
    @ExceptionHandler(RuntimeException.class)
    public <T> ServerResponseEntity<T>  handlerRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发送未知异常",requestURI,e);
        return ServerResponseEntity.showFailMsg(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ServerResponseEntity<T>  handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        // 所有参数异常信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        String message = allErrors.get(0).getDefaultMessage();
        log.info(message);

        return ServerResponseEntity.showFailMsg(message);
    }

    @ExceptionHandler(BindException.class)
    public <T> ServerResponseEntity<T> handlerBindException(BindException e) {
        log.info(e.getMessage());
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ServerResponseEntity.showFailMsg(message);
    }

}
