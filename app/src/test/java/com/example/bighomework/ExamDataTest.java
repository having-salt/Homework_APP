package com.example.bighomework;

import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExamDataTest {
    @Test
    public void ExamDataTest()throws Exception{
        ExamData ed = new ExamData();
        ed.addExam("test2","ming");
        ed.addGrade("test2",new Grade("hi",100.0));
        ed.addGrade("test2",new Grade("ho",100.0));
        ed.deleteGrade("test2","ho");
        List<Exam> exams = ed.getAllExams();
        for(Exam e :exams){
            System.out.println(e.getExamName());
        }
        Map<String,String> map = ed.getExamToTea();
        Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        ed.deleteExam("test2");
    }
}
