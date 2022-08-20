package com.changji.cloud.common.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:29 2022/8/20
 * @ Modified By：
 */
public class BaseModel implements Serializable {

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseModel{" + "createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }
}
