package com.itycu.server.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 本月调配页面数据统计
 */
public class IndexBenYueXunJianCountVO {


    /**
     * 巡检单位
     */
    private String xunjianCompany;

    /**
     * 巡检数量
     */
    private Integer xunjianCount;

    /**
     * 巡检时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date xunjianTime;

    /**
     * 巡检状态
     */
    private Integer xunjianStatus;

    /**
     * 巡检状态汉字值
     */
    private String xunjianText;


    public String getXunjianCompany() {
        return xunjianCompany;
    }

    public void setXunjianCompany(String xunjianCompany) {
        this.xunjianCompany = xunjianCompany;
    }

    public Integer getXunjianCount() {
        return xunjianCount;
    }

    public void setXunjianCount(Integer xunjianCount) {
        this.xunjianCount = xunjianCount;
    }

    public Date getXunjianTime() {
        return xunjianTime;
    }

    public void setXunjianTime(Date xunjianTime) {
        this.xunjianTime = xunjianTime;
    }

    public Integer getXunjianStatus() {
        return xunjianStatus;
    }

    public void setXunjianStatus(Integer xunjianStatus) {
        this.xunjianStatus = xunjianStatus;
    }

    public String getXunjianText() {
        return xunjianText;
    }

    public void setXunjianText(String xunjianText) {
        this.xunjianText = xunjianText;
    }

    @Override
    public String toString() {
        return "IndexBenYueXunJianCountVO{" +
                "xunjianCompany='" + xunjianCompany + '\'' +
                ", xunjianCount=" + xunjianCount +
                ", xunjianTime=" + xunjianTime +
                ", xunjianStatus=" + xunjianStatus +
                ", xunjianText='" + xunjianText + '\'' +
                '}';
    }
}
