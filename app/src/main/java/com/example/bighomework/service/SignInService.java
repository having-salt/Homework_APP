package com.example.bighomework.service;

import com.example.bighomework.model.Sign;
import com.example.bighomework.setting.IPSetting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignInService {
    public void addSign(String signName,long limit) throws IOException {
        System.out.println("等待连接服务端！");
        Socket socket = new Socket(IPSetting.IP, Integer.parseInt(IPSetting.sign_port));
        System.out.println("连接服务端成功！");
        String s = String.format("start\n%s\n%s",signName,limit);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(s.getBytes(StandardCharsets.UTF_8));
    }
    public void signIn(String signName,String stuName,String stuAccount) throws IOException{
        System.out.println("等待连接服务端！");
        Socket socket = new Socket(IPSetting.IP, Integer.parseInt(IPSetting.sign_port));
        System.out.println("连接服务端成功！");
        String s = String.format("sign\n%s\n%s\n%s",signName,stuName,stuAccount);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(s.getBytes(StandardCharsets.UTF_8));
    }
    public List<Sign> getAllSigns() throws IOException {
        System.out.println("等待连接服务端！");
        Socket socket = new Socket(IPSetting.IP, Integer.parseInt(IPSetting.sign_port));
        System.out.println("连接服务端成功！");
        String s = String.format("get");

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(s.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        int read = inputStream.read(bytes);
        List<Sign> list = new ArrayList<>();
        if(read>0){
            String[] split = new String(bytes, 0, read, StandardCharsets.UTF_8).split("\n");
            for(String sp:split){
                String[] split2 = sp.split("\t");
                list.add(new Sign(split2[0],stampToDate(split2[1]),split2[2]));
            }
        }
        return list;
    }
    public List<String> getResult(String signName) throws IOException{
        System.out.println("等待连接服务端！");
        Socket socket = new Socket(IPSetting.IP, Integer.parseInt(IPSetting.sign_port));
        System.out.println("连接服务端成功！");
        String s = String.format("result\n"+signName);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(s.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        int read = inputStream.read(bytes);
        List<String> list = new ArrayList<>();
        if(read>0){
            String[] split = new String(bytes, 0, read, StandardCharsets.UTF_8).split("\n");
            for(String sp:split){
                list.add(sp);
            }
        }
        return list;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
