package com.example.bighomework;

import com.example.bighomework.model.Sign;
import com.example.bighomework.service.SignInService;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SignInServiceTest {
    @Test
    public void SignInTest() throws IOException {
        SignInService service = new SignInService();
//        service.addSign("Test2",100);
        service.signIn("haha","木头","stu124");
        List<Sign> list1 = service.getAllSigns();
//        List<String> list2 = service.getResult("Test2");
        for(Sign s:list1){
            System.out.println(s.toString());
        }
//        for(String s:list2){
//            System.out.println(s);
//        }
    }
}
