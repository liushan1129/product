package com.bean.sale;

import java.io.Serializable;

public class SaleParams implements Serializable {


    //类型id
    private Long typeId;

    //厂商id
    private Long partnerId;


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }
}
