package com.changji.cloud.api.website.vo;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 16:11 2022/8/20
 * @ Modified By：
 */
public class AuthAccountVO {

    /**
     * 学号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "AuthAccountVO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", institute='" + institute + '\'' +
                ", specialty='" + specialty + '\'' +
                ", classes='" + classes + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
