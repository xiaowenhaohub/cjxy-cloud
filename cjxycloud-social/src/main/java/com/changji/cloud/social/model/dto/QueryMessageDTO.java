package com.changji.cloud.social.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author 小问号
 * @date 2023/3/8 15
 * description
 */

public class QueryMessageDTO {

    private Integer startIndex;

    private Integer pageSize;

    @NotBlank(message = "对方学号不能为空")
    private String account;

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "QueryMessageDTO{" +
                "startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                ", account='" + account + '\'' +
                '}';
    }
}
