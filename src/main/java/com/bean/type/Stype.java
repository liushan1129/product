package com.bean.type;

import java.io.Serializable;
import java.sql.Timestamp;

public class Stype implements Serializable {

    //id
    private Long id;
    //类型名称
    private String name;
    //添加人
    private Long addUserId;
    //添加时间
    private Timestamp addTime;

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

    public Long getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
}

