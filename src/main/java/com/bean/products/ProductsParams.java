package com.bean.products;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductsParams implements Serializable {

    //产品编号
    private String productNO;
    //产品类型
    private Long typeId;
    //添加时间
    private Timestamp addTime;

    public String getProductNO() {
        return productNO;
    }

    public void setProductNO(String productNO) {
        this.productNO = productNO;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

}
