package com.changji.cloud.social.model.vo;

/**
 * @author 小问号
 * @date 2023/3/7 09
 * description
 */

public class UserRelationshipVO {

    String userAccount;

    String avatar;

    String alias;

    public String getUserAccount() {
        return userAccount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
