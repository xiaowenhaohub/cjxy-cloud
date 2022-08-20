package com.changji.cloud.common.core.exception;

import com.changji.cloud.common.core.response.ResponseEnum;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * @author 小问号
 * @date 2022/8/4 15
 * description
 */

public class ServiceException extends RuntimeException{

    private ResponseEnum responseEnum;

    private String message;

    public ServiceException(){}

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, ResponseEnum responseEnum ) {
        this.responseEnum = responseEnum;
        this.message = message;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
