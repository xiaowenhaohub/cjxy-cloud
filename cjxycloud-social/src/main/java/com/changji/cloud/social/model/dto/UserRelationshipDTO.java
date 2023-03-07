package com.changji.cloud.social.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 小问号
 * @date 2023/3/7 10
 * description
 */

public class UserRelationshipDTO {

    @NotBlank(message = "用户学号不能为空")
    private String account;

    private String alias;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
