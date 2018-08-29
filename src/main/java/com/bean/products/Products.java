package com.bean.products;

import java.io.Serializable;
import java.sql.Timestamp;

public class Products implements Serializable {

    //id
    private Long id;
    //产品编号
    private String productNO;
    //产品类型
    private Long typeId;
    //数量
    private Integer quantity;
    //添加时间
    private Timestamp addTime;
    //添加人id
    private Long addUserId;
    //修改时间
    private Timestamp updateTime;
    //修改人id
    private Long updateUserId;
    //修改原因
    private String updateReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }
}
