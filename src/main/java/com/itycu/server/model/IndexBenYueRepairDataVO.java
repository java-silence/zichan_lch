package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 本月报修数量的统计页面展示
 */
public class IndexBenYueRepairDataVO {


    /**
     * 报修单位
     */
    private String repairCompany;

    /**
     * 报修数量
     */
    private int repairCount;


    /**
     * 报修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    private Date repairTime;

    /**
     * 报修状态
     */
    private String repairStatus;


    private String statusText;

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


    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    private String dataStr;

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    @Override
    public String toString() {
        return "IndexBenYueRepairDataVO{" +
                "repairCompany='" + repairCompany + '\'' +
                ", repairCount=" + repairCount +
                ", repairTime=" + repairTime +
                ", repairStatus='" + repairStatus + '\'' +
                '}';
    }

    public String getRepairCompany() {
        return repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }
}
