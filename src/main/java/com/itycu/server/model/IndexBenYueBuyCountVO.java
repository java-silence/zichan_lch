package com.itycu.server.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 本月采购页面数据信息展示的实体信息
 */
public class IndexBenYueBuyCountVO {


    /**
     * 采购单位
     */
    private String buyCompany;

    /**
     * 物资总数
     */
    private Integer goodsTotal;


    /**
     * 购买时间
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private Date buyTime;

    /**
     * 购买状态
     */
    private String buyStatus;

    /**
     * 购买状态对饮汉字
     */
    private String statusText;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getBuyCompany() {
        return buyCompany;
    }

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

    @Override
    public String toString() {
        return "IndexBenYueBuyDataCountVO{" +
                "buyCompany='" + buyCompany + '\'' +
                ", goodsTotal=" + goodsTotal +
                ", buyTime=" + buyTime +
                ", buyStatus='" + buyStatus + '\'' +
                '}';
    }

    public void setBuyCompany(String buyCompany) {
        this.buyCompany = buyCompany;
    }

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }
}
