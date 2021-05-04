package com.example.demo.pojo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
public class VoEmployee implements java.io.Serializable{
    private Integer empId;
    private String empName;
    @JsonIgnore
    private transient String empPassword;
    private String empSex;
    private Integer empAge;
    private Date empBirthDay;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Date getEmpBirthDay() {
        return empBirthDay;
    }

    public void setEmpBirthDay(Date empBirthDay) {
        this.empBirthDay = empBirthDay;
    }

    public VoEmployee() {
    }

    public VoEmployee(Integer empId, String empName, String empPassword, String empSex, Integer empAge, Date empBirthDay) {
        this.empId = empId;
        this.empName = empName;
        this.empPassword = empPassword;
        this.empSex = empSex;
        this.empAge = empAge;
        this.empBirthDay = empBirthDay;
    }

    @Override
    public String toString() {
        return "VoEmployee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empPassword='" + empPassword + '\'' +
                ", empSex='" + empSex + '\'' +
                ", empAge=" + empAge +
                ", empBirthDay=" + empBirthDay +
                '}';
    }
}
