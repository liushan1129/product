package com.bean.partner;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 厂商信息
 */
public class Partner implements Serializable {

    //id
    private Long id;
    //合作方名称
    private String pName;
    //地址
    private String address;
    //管理人
    private String userName;
    //联系电话
    private String mobile;
    //描述
    private String descript;
    //添加时间
    private Timestamp addTime;
    //添加人id
    private Long addUserId;

    //表示是原料提供商还是销售对象
    private Integer mark; //1.原料供应商，2.销售对象


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Long getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
