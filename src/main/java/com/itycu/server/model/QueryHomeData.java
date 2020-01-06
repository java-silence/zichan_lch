package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class QueryHomeData {
    /**
     * 部门名称
     */
    public String deptName;
    /**
     * 数量
     */
    public String countTotal;
    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    public Date dateTime;
    /**
     * 状态
     */
    public String statusText;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(String countTotal) {
        this.countTotal = countTotal;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }



}
