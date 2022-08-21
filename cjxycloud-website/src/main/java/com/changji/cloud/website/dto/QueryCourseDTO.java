package com.changji.cloud.website.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:34 2022/8/21
 * @ Modified By：
 */
public class QueryCourseDTO {

    @ApiModelProperty("周次")
    private String weekly;

    @ApiModelProperty("学期")
    private String semester;

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "QueryCourseDTO{" +
                "weekly='" + weekly + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
