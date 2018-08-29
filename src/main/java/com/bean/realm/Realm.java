package com.bean.realm;

import java.io.Serializable;
import java.sql.Timestamp;

public class Realm implements Serializable {

    //id
    private Long id;
    //权限名称
    private String name;
    //描述
    private String descript;
    //添加时间
    private Timestamp addTime;
    //添加人id
    private Long addUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
