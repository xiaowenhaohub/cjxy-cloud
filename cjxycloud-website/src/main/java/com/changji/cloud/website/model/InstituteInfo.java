package com.changji.cloud.website.model;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:18 2022/8/23
 * @ Modified By： 学院信息表
 */
public class InstituteInfo {

    /**
     * 学院编号
     */
    private String id;

    /**
     * 学院名称
     */
    private String institute;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "InstituteInfo{" +
                "id='" + id + '\'' +
                ", institute='" + institute + '\'' +
                '}';
    }
}
