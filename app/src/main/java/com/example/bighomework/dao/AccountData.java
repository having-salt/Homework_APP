package com.example.bighomework.dao;

import android.util.Log;

import com.example.bighomework.service.LoginService;
import com.example.bighomework.setting.IPSetting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountData {
    private String username;
    private String sql_password;
    private String url;

    public AccountData(){
        username = IPSetting.username;
        sql_password = IPSetting.password;
        url = String.format("jdbc:mysql://%s:%s/information",IPSetting.IP,IPSetting.port);
    }
    public void addAccount(String account, String type, String password, String name,String sentence,String school)throws Exception{
        if(getNameByAccount(account)!=null){
            throw new RuntimeException("该账号已存在");
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("insert into account values('%s','%s','%s','%s','%s','%s',null);",account,type,password,name,sentence,school);
        PreparedStatement pstm = conn.prepareStatement(sql);
        int update = pstm.executeUpdate();
        System.out.println(update);

        String path = "src/main/res/drawable/";
        if(new LoginService().isTeacher(account)){
            updateImg(path+"test_photo.png",account);
        }else{
            updateImg(path+"test_photo2.png",account);
        }

        pstm.close();
        conn.close();
    }
    public boolean isCorrect(String account,String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        if(getNameByAccount(account)==null){
            return false;
        }
        String sql = String.format("select password from account where account = '%s'",account);
        System.out.println(sql);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            if(password.equals(rs.getString("password"))){
                return true;
            }
        }
        pstm.close();
        conn.close();
        return false;
    }
    public boolean updateImg(String imgPath,String account)throws Exception{
        boolean flag = false;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        String sql = "update account set photo = ? where account = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        InputStream is = new FileInputStream(imgPath);
        pstmt.setBlob(1,is);
        pstmt.setString(2,account);
        int i = pstmt.executeUpdate();
        if(i>0){
            flag = true;
        }
        return flag;
    }
    public void downloadImg(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        String sql=String.format("select photo from account where account='%s'",account);
        PreparedStatement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()) {
            Blob blob=resultSet.getBlob(1);
            InputStream is=blob.getBinaryStream();

            FileOutputStream os=new FileOutputStream("src/main/res/drawable/photo.png");
            int len=0;
            byte[] buf=new byte[1024];
            while((len=is.read(buf))!=-1) {
                os.write(buf,0,len);
            }
            os.flush();
            os.close();
            is.close();
        }
        statement.close();
        conn.close();
    }
    public void deleteAccount(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("delete from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);

        int update = pstm.executeUpdate();
        System.out.println(update);

        pstm.close();
        conn.close();
    }
    public String getNameByAccount(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        Log.d("tag",account);
        while(rs.next()){
            return rs.getString("name");
        }
        pstm.close();
        conn.close();
        return null;
    }
    public String getSentenceByAccount(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("sentence");
        }
        pstm.close();
        conn.close();
        return null;
    }
    public String getSchoolByAccount(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("school");
        }
        pstm.close();
        conn.close();
        return null;
    }
    public String getPasswordByAccount(String account)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, sql_password);
        System.out.println(conn);

        String sql = String.format("select * from account where account = '%s';",account);
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            return rs.getString("password");
        }
        pstm.close();
        conn.close();
        return null;
    }
}
