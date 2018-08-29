package com.bean.type;

import java.io.Serializable;
import java.sql.Timestamp;

public class TypeParams implements Serializable {
    //类型名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

