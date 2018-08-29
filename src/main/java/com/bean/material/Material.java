package com.bean.material;

import java.io.Serializable;
import java.sql.Timestamp;

public class Material implements Serializable {

    //id
    private Long id;
    //厂商id
    private Long partnerId;
    //原料名称
    private String name;
    //进货时间
    private Timestamp intoTime;
    //进货总数
    private Integer totalMeter;
    //金额
    private Double money;
    //添加人
    private Long addUserId;
    //添加时间
    private Timestamp addTime;
    //备注
    private String remark;


    //TODO 暂时不用，后期考虑
//    //实际得到数
//    private Integer realMeter;
//    //下次约定交货时间
//    private Timestamp nextgiveTime;
//    //最后一次给的米数
//    private Integer lastgiveMeter;
//    //修改人
//    private Long updateUserId;
//    //修改时间
//    private Timestamp updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(Timestamp intoTime) {
        this.intoTime = intoTime;
    }

    public Integer getTotalMeter() {
        return totalMeter;
    }

    public void setTotalMeter(Integer totalMeter) {
        this.totalMeter = totalMeter;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
