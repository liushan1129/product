package com.bean.partner;


import java.sql.Timestamp;

public class PartnerParams {

    //合作方名称
    private String pName;
    //用户名
    private String userName;
    //手机号码
    private String mobile;
    //地址
    private String address;
    //添加时间
    private Timestamp addTime; //添加时间

    private Timestamp mark;

    public PartnerParams() {
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getMark() {
        return mark;
    }

    public void setMark(Timestamp mark) {
        this.mark = mark;
    }
}
