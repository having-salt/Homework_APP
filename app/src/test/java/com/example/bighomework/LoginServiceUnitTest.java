package com.example.bighomework;

import com.example.bighomework.service.LoginService;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginServiceUnitTest {
    @Test
    public void TestIsTeacher(){
        LoginService ls = new LoginService();
        try{
            Assert.assertTrue(ls.isTeacher("tea123"));
            Assert.assertFalse(ls.isTeacher("stu123"));
        }catch (Exception e) {
           Assert.assertTrue(false);
        }
    }
}