package com.itycu.server.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 本月调配页面数据统计
 */
public class IndexBenYueBaoFeiCountVO {


    /**
     * 报废单位
     */
    private String baofeiCompany;

    /**
     * 报废数量
     */
    private Integer baofeiCount;

    /**
     * 报废时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    private Date baofeiTime;

    /**
     * 报废状态
     */
    private Integer baofeiStatus;

    /**
     * 报废状态页面显示值
     */
    private String baofeiText;
    private Integer wanCheng;
    private Integer weiWanCheng;

    public Integer getWanCheng() {
        return wanCheng;
    }

    public void setWaiCheng(Integer wanCheng) {
        this.wanCheng = wanCheng;
    }

    public Integer getWeiWanCheng() {
        return weiWanCheng;
    }

    public void setWeiWanCheng(Integer weiWanCheng) {
        this.weiWanCheng = weiWanCheng;
    }


    public String getBaofeiCompany() {
        return baofeiCompany;
    }

    public void setBaofeiCompany(String baofeiCompany) {
        this.baofeiCompany = baofeiCompany;
    }

    public Integer getBaofeiCount() {
        return baofeiCount;
    }

    public void setBaofeiCount(Integer baofeiCount) {
        this.baofeiCount = baofeiCount;
    }

    public Date getBaofeiTime() {
        return baofeiTime;
    }

    public void setBaofeiTime(Date baofeiTime) {
        this.baofeiTime = baofeiTime;
    }

    public Integer getBaofeiStatus() {
        return baofeiStatus;
    }

    public void setBaofeiStatus(Integer baofeiStatus) {
        this.baofeiStatus = baofeiStatus;
    }

    public String getBaofeiText() {
        return baofeiText;
    }

    public void setBaofeiText(String baofeiText) {
        this.baofeiText = baofeiText;
    }

    @Override
    public String toString() {
        return "IndexBenYueBaoFeiCountVO{" +
                "baofeiCompany='" + baofeiCompany + '\'' +
                ", baofeiCount=" + baofeiCount +
                ", baofeiTime=" + baofeiTime +
                ", baofeiStatus=" + baofeiStatus +
                ", baofeiText='" + baofeiText + '\'' +
                '}';
    }
}
