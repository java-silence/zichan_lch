package com.itycu.server.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 本月调配页面数据统计
 */
public class IndexBenYueDiaoPeiCountVO {


    /**
     * 调配单位
     */
    private String diaoPeiCompany;

    /**
     * 调配数量
     */
    private Integer diaoPeiCount;


    private String dataStr;

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    /**
     * 调配时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    private Date diaoPeiTime;

    /**
     * 调配状态
     */
    private Integer diaoPeiStatus;

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

    /**
     * 调配汉字值
     */
    private String diaoPeiText;


    public String getDiaoPeiCompany() {
        return diaoPeiCompany;
    }

    public void setDiaoPeiCompany(String diaoPeiCompany) {
        this.diaoPeiCompany = diaoPeiCompany;
    }

    public Integer getDiaoPeiCount() {
        return diaoPeiCount;
    }

    public void setDiaoPeiCount(Integer diaoPeiCount) {
        this.diaoPeiCount = diaoPeiCount;
    }

    public Date getDiaoPeiTime() {
        return diaoPeiTime;
    }

    public void setDiaoPeiTime(Date diaoPeiTime) {
        this.diaoPeiTime = diaoPeiTime;
    }

    public Integer getDiaoPeiStatus() {
        return diaoPeiStatus;
    }

    public void setDiaoPeiStatus(Integer diaoPeiStatus) {
        this.diaoPeiStatus = diaoPeiStatus;
    }

    public String getDiaoPeiText() {
        return diaoPeiText;
    }

    public void setDiaoPeiText(String diaoPeiText) {
        this.diaoPeiText = diaoPeiText;
    }

    @Override
    public String toString() {
        return "IndexBenYueDiaoPeiCountVO{" +
                "diaoPeiCompany='" + diaoPeiCompany + '\'' +
                ", diaoPeiCount=" + diaoPeiCount +
                ", diaoPeiTime=" + diaoPeiTime +
                ", diaoPeiStatus=" + diaoPeiStatus +
                ", diaoPeiText='" + diaoPeiText + '\'' +
                '}';
    }
}
