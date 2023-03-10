package com.changji.cloud.social.model;

import com.changji.cloud.common.core.model.BaseModel;

/**
 * @author 小问号
 * @date 2023/3/6 09
 * description
 */

public class ChatMessages extends BaseModel {

    private Long id;

    private int code;

    //消息类型
    private String type;

    private String fromUserAccount;

    private String toUserAccount;

    private String content;

    private Integer state;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromUserAccount() {
        return fromUserAccount;
    }

    public void setFromUserAccount(String fromUserAccount) {
        this.fromUserAccount = fromUserAccount;
    }

    public String getToUserAccount() {
        return toUserAccount;
    }

    public void setToUserAccount(String toUserAccount) {
        this.toUserAccount = toUserAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChatMessages{" +
                "id=" + id +
                ", fromUserAccount='" + fromUserAccount + '\'' +
                ", toUserAccount='" + toUserAccount + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }
}
