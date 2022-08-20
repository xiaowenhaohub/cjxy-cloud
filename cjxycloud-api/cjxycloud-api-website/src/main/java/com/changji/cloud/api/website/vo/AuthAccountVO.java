package com.changji.cloud.api.website.vo;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:11 2022/8/20
 * @ Modified By：
 */
public class AuthAccountVO {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthAccountVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
