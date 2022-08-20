package com.changji.cloud.common.core.response;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:18 2022/7/20
 * @ Modified By：
 */
public enum ResponseEnum {

    /**
     * ok
     */
    OK(200, "ok"),

    /**
     * 用于直接显示提示用户的错误，内容由输入内容决定
     */
    SHOW_FAIL(400, ""),

    /**
     * 用户名和密码错误
     */
    ACCOUNT_ERROR(400, "用户名或密码错误"),

    /**
     * 方法参数没有校验，内容由输入内容决定
     */
    METHOD_ARGUMENT_NOT_VALID(4230, ""),

    /**
     * 无法读取获取请求参数
     */
    HTTP_MESSAGE_NOT_READABLE(4231, "请求参数格式有误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(4232, "Unauthorized"),

    /**
     * 服务器出了点小差
     */
    EXCEPTION(4233, "服务器出了点小差"),

    /**
     * 数据异常
     */
    DATA_ERROR(4234, "数据异常，请刷新后重新操作"),

    /**
     * 一些需要登录的接口，而实际上因为前端无法知道token是否已过期，导致token已失效时，
     * 应该返回一个状态码，告诉前端token已经失效了，及时清理
     */
    CLEAN_TOKEN(1001, "clean token"),

    /**
     * 刷新token已过期
     */
    REFRESH_TOKEN_EXIST(1002, "refresh token exist"),

    /**
     * 数据不完整
     */
    DATA_INCOMPLETE(1003, "数据不完整"),

    /**
     * 04 开头代表注册登录之类的异常状态
     * 社交账号未绑定，当前端看到该异常时，应该在合适的时间（比如在购买的时候跳）根据社交账号的类型，跳转到绑定系统账号的页面
     */
    SOCIAL_ACCOUNT_NOT_BIND(2001, "social account not bind"),

    /**
     * 有些时候第三方系统授权之后，会有个临时的key，比如小程序的session_key
     * 这个异常代表session_key过期，前端遇到这个问题的时候，应该再次调用社交登录的接口，刷新session_key
     */
    BIZ_TEMP_SESSION_KEY_EXPIRE(2002, "biz temp session key expire"),

    /**
     * 账号未注册，前端看到这个状态码，弹出选择框，提示用户账号未注册，是否进入注册页面，用户选择是，进入注册页面
     */
    ACCOUNT_NOT_REGISTER(2003, "account not register");

    private final Integer code;

    private final String msg;

    public Integer value() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
    }

}
