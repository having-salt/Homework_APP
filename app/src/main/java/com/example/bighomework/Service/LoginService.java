package com.example.bighomework.Service;

import java.util.regex.Pattern;

public class LoginService {
    public boolean isTeacher(String account) throws RuntimeException{
        String pattern = "(stu.+)|(tea.+)";
        boolean isMatch = Pattern.matches(pattern, account);
        if(isMatch){
            String teacherPattern = "tea.+";
            return Pattern.matches(teacherPattern, account);
        }else{
            throw new RuntimeException("学号/职工号输入格式错误："+account);
        }
    }
}
