package com.changji.cloud.api.user.feign.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 2:57 2022/8/23
 * @ Modified By：
 */
public class UserDTO {

    /**
     * 用户id
     */
    @NotNull(message = "userId不能为空")
    private Long userId;

    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空")
    private String account;

    /**
     *  院校
     */
    @NotBlank(message = "院校不能为空")
    private String institute;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空")
    private String specialty;

    /**
     * 班级
     */
    @NotBlank(message = "班级不能为空")
    private String classes;

    /**
     * 真实姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String realName;


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

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", institute='" + institute + '\'' +
                ", specialty='" + specialty + '\'' +
                ", classes='" + classes + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
