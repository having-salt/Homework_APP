package com.example.bighomework.dao;

import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamData {
    private final String username = "root";
    private final String password = "123456";
    private final String url = "jdbc:mysql://localhost:3306/exam";

    public List<Exam> getAllExams() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/exam";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);

        String sql = "show tables;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<Exam> exams= new ArrayList<>();
        while (rs.next()){
            String examName = rs.getString(1);
            if(examName.equals("teacher")){
                continue;
            }
            Exam exam = new Exam(examName);
            exams.add(exam);
            String sql2 = "select * from "+examName;
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstm2.executeQuery();
            while(rs2.next()){
                exam.addGrade(new Grade(rs2.getString("stuName"),rs2.getDouble("grade")));
                System.out.println(rs2.getString("stuName")+rs2.getString("grade"));
            }
            pstm2.close();
        }

        pstm.close();
        conn.close();
        return new ArrayList<>(exams);
    }
    public Map<String,String> getExamToTea() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/exam";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);

        String sql = "select * from teacher;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        Map<String,String> map = new HashMap<>();
        while(rs.next()){
            map.put(rs.getString("examName"),rs.getString("teaName"));
        }
        pstm.close();
        conn.close();
        return new HashMap<>(map);
    }
    public void addExam(String examName,String teaName)throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            // url错误创建链接时会出现异常
            // 参数错误不会导致运行时异常
            System.out.println(conn);

            String sql = "create table " + " if not exists "+ examName +" (stuName varchar(255), grade float);";
            PreparedStatement pstm = conn.prepareStatement(sql);
            // 4.发送执行SQL
            int update = pstm.executeUpdate();
            System.out.println(update);
            // 5.（如果是查询语句，需要处理结果集）
            // 6.关闭资源
            String sql2 = String.format("insert into teacher values ('%s','%s');",teaName,examName);
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            // 4.发送执行SQL
            int update2 = pstm2.executeUpdate();
            System.out.println(update2);
            pstm.close();
            conn.close();

    }
    public void deleteExam(String examName) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        // url错误创建链接时会出现异常
        // 参数错误不会导致运行时异常
        System.out.println(conn);

        String sql = "drop table " + examName + ";";
        PreparedStatement pstm = conn.prepareStatement(sql);
        // 4.发送执行SQL
        int update = pstm.executeUpdate();
        System.out.println(update);
        // 5.（如果是查询语句，需要处理结果集）
        // 6.关闭资源
        pstm.close();
        conn.close();
    }
    public void addGrade(String examName, Grade grade)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);

        String sql1 = "show tables;";
        PreparedStatement pstm1 = conn.prepareStatement(sql1);
        ResultSet rs = pstm1.executeQuery();

        boolean flag = true;
        while (rs.next()){
           if(rs.getString(1).equals(examName)){
               flag = false;
           }
        }
        if(flag){
            throw new RuntimeException("该考试不存在");
        }
        String sql2 = "insert into "+examName+" values ('"+grade.getStuName()+"',"+grade.getGrade()+");";
        System.out.println(sql2);
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        int update = pstm2.executeUpdate();
        System.out.println(update);

        pstm1.close();
        pstm2.close();
        conn.close();
    }
    public void deleteGrade(String examName,String stuName)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/exam";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);

        String sql1 = "show tables;";
        PreparedStatement pstm1 = conn.prepareStatement(sql1);
        ResultSet rs = pstm1.executeQuery();

        boolean flag = true;
        while (rs.next()){
            if(rs.getString(1).equals(examName)){
                flag = false;
            }
        }
        if(flag){
            throw new RuntimeException("该考试不存在");
        }
        String sql2 = "delete from "+examName+" where "+"stuName = "+"'"+stuName+"'"+";";
        System.out.println(sql2);
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        int update = pstm2.executeUpdate();
        System.out.println(update);

        pstm1.close();
        pstm2.close();
        conn.close();
    }
}
