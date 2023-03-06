package com.changji.cloud.social.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @author 小问号
 * @date 2023/3/6 13
 * description
 */

public class UserRelationship extends BaseModel {

    private Long id;

    private String userAccount;

    private String friendAccount;

    private Integer relState;

    private String aliasUser;

    private String aliasFriend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    public Integer getRelState() {
        return relState;
    }

    public void setRelState(Integer relState) {
        this.relState = relState;
    }

    public String getAliasUser() {
        return aliasUser;
    }

    public void setAliasUser(String aliasUser) {
        this.aliasUser = aliasUser;
    }

    public String getAliasFriend() {
        return aliasFriend;
    }

    public void setAliasFriend(String aliasFriend) {
        this.aliasFriend = aliasFriend;
    }

    @Override
    public String toString() {
        return "UserRelationship{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", friendAccount='" + friendAccount + '\'' +
                ", relState=" + relState +
                ", aliasUser='" + aliasUser + '\'' +
                ", aliasFriend='" + aliasFriend + '\'' +
                '}';
    }
}
