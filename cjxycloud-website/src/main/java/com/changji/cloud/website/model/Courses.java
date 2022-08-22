package com.changji.cloud.website.model;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:46 2022/8/22
 * @ Modified By：
 */
public class Courses {


    /**
     * 班级
     */
    private String classes;

    /**
     * 学期
     */
    private String semester;

    /**
     * 周次
     */
    private String weekly;

    /**
     * 院校
     */
    private String institute;

    /**
     * 专业
     */
    private String specialty;

    /**
     * json课程表
     */
    private String courseInfo;

    /**
     * 状态
     */
    private Integer status;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "classes='" + classes + '\'' +
                ", semester='" + semester + '\'' +
                ", weekly='" + weekly + '\'' +
                ", institute='" + institute + '\'' +
                ", specialty='" + specialty + '\'' +
                ", courseInfo='" + courseInfo + '\'' +
                ", status=" + status +
                '}';
    }
}