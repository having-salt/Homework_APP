package com.example.bighomework;

import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;

import org.junit.Test;

import java.util.List;

public class ExamDataTest {
    @Test
    public void ExamDataTest()throws Exception{
        ExamData ed = new ExamData();
        ed.addExam("test1");
        ed.addGrade("test1",new Grade("hi",100.0));
        ed.addGrade("test1",new Grade("ho",100.0));
        ed.deleteGrade("test1","ho");
        List<Exam> exams = ed.getAllExams();
        for(Exam e :exams){
            System.out.println(e.getExamName());
        }
        ed.deleteExam("test1");
    }
}
