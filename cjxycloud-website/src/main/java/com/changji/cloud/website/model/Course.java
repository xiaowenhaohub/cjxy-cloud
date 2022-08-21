package com.changji.cloud.website.model;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 13:00 2022/8/20
 * @ Modified By：
 */
public class Course {

    /**
     *课程名称
     */
    private String courseName;

    /**
     *任课老师
     */
    private String teacherName;

    /**
     *课程周次
     */
    private String weekly;

    /**
     *课程教室
     */
    private String courseRoom;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", weekly='" + weekly + '\'' +
                ", courseRoom='" + courseRoom + '\'' +
                '}';
    }
}
