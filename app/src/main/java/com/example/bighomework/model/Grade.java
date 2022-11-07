package com.example.bighomework.model;

public class Grade {
    private String StuName;
    private double grade;

    public Grade() {
    }

    public Grade(String stuName, double grade) {
        StuName = stuName;
        this.grade = grade;
    }


    public String getStuName() {
        return StuName;
    }

    public double getGrade() {
        return grade;
    }


    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
