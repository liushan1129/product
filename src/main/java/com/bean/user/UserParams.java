package com.bean.user;


import java.io.Serializable;
import java.sql.Date;

public class UserParams implements Serializable {

    //用户名
    private String userName;
    //手机号码
    private String mobile;
    //地址
    private String address;
    //添加时间
    private String startTime;

    private String endTime;

    public UserParams() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
