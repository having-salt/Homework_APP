package com.example.bighomework.dao;

import com.example.bighomework.setting.IPSetting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountData {
    private String username;
    private String password;
    private String url;
    private Connection conn;

    public AccountData(){
        try{
            username = IPSetting.username;
            password = IPSetting.password;
            url = String.format("jdbc:mysql://%s:%s/information",IPSetting.IP,IPSetting.port);
            conn = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addAccount(String account, String type, String password, String name,String sentence,String school)throws Exception{
        if(getNameByAccount(account)!=null){
            throw new RuntimeException("该账号已存在");
        }
        Class.forName("com.mysql.cj.jdbc.Driver");

        // url错误创建链接时会出现异常
        // 参数错误不会导致运行时异常
        System.out.println(conn);

        String sql = String.format("insert into account values('%s','%s','%s','%s','%s','%s');",account,type,password,name,sentence,school);
        PreparedStatement pstm = conn.prepareStatement(sql);
        // 4.发送执行SQL
        int update = pstm.executeUpdate();
        System.out.println(update);
        // 5.（如果是查询语句，需要处理结果集）
        // 6.关闭资源
        pstm.close();
        conn.close();
    }
    public void deleteAccount(String account)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println(conn);

        String sql = String.format("delete from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);

        int update = pstm.executeUpdate();
        System.out.println(update);

        pstm.close();
        conn.close();
    }
    public String getNameByAccount(String account)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("name");
        }
        return null;
    }
    public String getSentenceByAccount(String account)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("sentence");
        }
        return null;
    }
    public String getSchoolByAccount(String account)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("school");
        }
        return null;
    }
}
