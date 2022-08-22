package com.changji.cloud.website.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;


/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:34 2022/8/21
 * @ Modified By：
 */
public class QueryCourseDTO {

    @ApiModelProperty("周次")
    @NotBlank
    private String weekly;

    @NotBlank
    @ApiModelProperty("学期")
    private String semester;

    @NotBlank
    @ApiModelProperty("院校")
    private String institute;

    @NotBlank
    @ApiModelProperty("班级")
    private String classes;

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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "QueryCourseDTO{" +
                "weekly='" + weekly + '\'' +
                ", semester='" + semester + '\'' +
                ", institute='" + institute + '\'' +
                ", classes='" + classes + '\'' +
                '}';
    }
}
