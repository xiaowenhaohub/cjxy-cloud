package com.changji.cloud.social.utils;

import com.alibaba.fastjson2.JSON;
import com.changji.cloud.common.security.utils.SecurityUtils;

/**
 * @author 小问号
 * @date 2023/3/6 14
 * description
 */

public class Result {

    private static class ResultCode {
        private final static int SUCCESS = 200;
        private final static int ERROR = 4040;

    }

    private Integer code;

    private Integer type;

    private String fromAccount;

    private String toAccount;

    private String message;

    private Object data;

    public Result(Integer code, String fromAccount, String toAccount, String message, Object data) {
        this.code = code;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.message = message;
        this.data = data;
    }

    public static boolean success(String message, Object data, String toAccount) {
        return send(ResultCode.SUCCESS,message,data, SessionUtils.getAccount(),toAccount);
    }

    public static boolean error(String message,Object data,String toAccount) {
        return send(ResultCode.ERROR,message,data, SessionUtils.getAccount(),toAccount);

    }

    public static boolean sendLocal(Integer code, String message,Object data){
        return send(ResultCode.SUCCESS,message,data, SessionUtils.getAccount(),SessionUtils.getAccount());
    }


    public static boolean send(Integer code, String message, Object data, String fromAccount, String toAccount) {
        String result = JSON.toJSONString(new Result(code,fromAccount,toAccount,message,data));
        return SessionUtils.sendMessage(result, toAccount);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
