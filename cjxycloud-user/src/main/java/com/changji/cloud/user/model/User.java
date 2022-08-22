package com.changji.cloud.user.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 2:48 2022/8/23
 * @ Modified By：
 */
public class User extends BaseModel {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 学号
     */
    private String account;

    /**
     *  院校
     */
    private String institute;

    /**
     * 专业
     */
    private String specialty;

    /**
     * 班级
     */
    private String classes;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String picture;

    /**
     * 状态 1 正常 0 无效
     */
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", institute='" + institute + '\'' +
                ", specialty='" + specialty + '\'' +
                ", classes='" + classes + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", picture='" + picture + '\'' +
                ", status=" + status +
                '}';
    }
}
