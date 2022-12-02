package com.example.bighomework;

import com.example.bighomework.dao.AccountData;

import org.junit.Assert;
import org.junit.Test;

public class AccountDataTest {
    @Test
    public void AccountTest()throws Exception{
        AccountData ad = new AccountData();
//        ad.addAccount("stu_admin","Student","admin","小米","有志者 事竟成","软件学院");
//        ad.addAccount("tea_admin","Student","admin","大米","有志者 事竟成","软件学院");
        Assert.assertTrue(ad.isCorrect("stu_admin","admin"));
    }
}
