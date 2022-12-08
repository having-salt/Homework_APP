package com.example.bighomework.model;

public class Sign {
    private String SignName;
    private String Time;//起始时间
    private String Limit;//限制时长

    public Sign(String signName, String time, String limit) {
        SignName = signName;
        Time = time;
        Limit = limit;
    }

    public String getSignName() {
        return SignName;
    }

    public String getTime() {
        return Time;
    }

    public String getLimit() {
        return Limit;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setLimit(String limit) {
        Limit = limit;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "SignName='" + SignName + '\'' +
                ", Time='" + Time + '\'' +
                ", Limit='" + Limit + '\'' +
                '}';
    }
}
