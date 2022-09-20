package com.changji.cloud.common.core.model;

import javax.validation.constraints.NotNull;

/**
 * @author 小问号
 * @date 2022/9/20 17
 * description
 */

public class Page {

    @NotNull(message = "页数不能为空")
    private Integer pageNum;

    @NotNull(message = "页面大小不能为空")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
