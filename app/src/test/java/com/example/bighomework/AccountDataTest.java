package com.example.bighomework;

import com.example.bighomework.dao.AccountData;

import org.junit.Test;

public class AccountDataTest {
    @Test
    public void AccountTest()throws Exception{
        AccountData ad = new AccountData();
        ad.addAccount("stu123","Student","123","小米");
    }
}
