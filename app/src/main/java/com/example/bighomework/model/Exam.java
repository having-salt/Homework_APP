package com.example.bighomework.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exam {
    private String ExamName;
    private List<Grade> gradeList;

    public Exam(String examName) {
        ExamName = examName;
    }

    public String getExamName() {
        return ExamName;
    }

    public List<Grade> getGradeList() {
        return new ArrayList<>(gradeList);
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }
    public boolean addGrade(Grade g){
        Iterator<Grade> it = gradeList.iterator();
        while(it.hasNext()){
            Grade temp = it.next();
            if(temp.getStuName().equals(g.getStuName())){
                return false;
            }
        }
        gradeList.add(g);
        return true;
    }
    public boolean deleteGradeByName(String stuName){
        boolean flag = false;
        Iterator<Grade> it = gradeList.iterator();
        while(it.hasNext()){
            Grade g = it.next();
            if(g.getStuName().equals(stuName)){
                flag = true;
                it.remove();
            }
        }
        return flag;
    }

}
