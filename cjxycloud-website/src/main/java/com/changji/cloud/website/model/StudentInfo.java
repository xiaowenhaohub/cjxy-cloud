package com.changji.cloud.website.model;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:17 2022/8/21
 * @ Modified By：
 */
public class StudentInfo {

    /**
     * 院校
     */
    private String institute;

    /**
     * 专业
     */
    private String specialty;

    /**
     * 学生班级
     */
    private String classes;

    /**
     * 学生姓名
     */
    private String studentName;

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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "institute='" + institute + '\'' +
                ", specialty='" + specialty + '\'' +
                ", classes='" + classes + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
