package com.changji.cloud.auth.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:31 2022/8/20
 * @ Modified By： 统一账户信息
 */
public class AuthAccount extends BaseModel {


    /**
     * 全平台用户唯一id
     */
    private Long uid;

    /**
     * 学号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建ip
     */
    private String createIp;

    /**
     * 状态 1:启用 0:禁用 -1:删除
     */
    private Integer status;

    /**
     * 用户id
     */
    private Long userId;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthAccount{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createIp='" + createIp + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
