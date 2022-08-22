package com.changji.cloud.auth.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:23 2022/8/20
 * @ Modified By：
 */
public class AuthenticationDTO {

    @NotBlank(message = "学号不能为空")
    @ApiModelProperty(value = "学号", required = true)
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

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

    @Override
    public String toString() {
        return "AuthenticationDTO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
