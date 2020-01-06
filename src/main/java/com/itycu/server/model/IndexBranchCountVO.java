package com.itycu.server.model;

import java.math.BigDecimal;

/**
 * 点击支行或者部门显示支行或者部门的资产概况
 */
public class IndexBranchCountVO {


    /**
     * 资产数量
     */
    private int zcNumber;

    /**
     * 综合办资产数量
     */
    private int zhbZcNumber;

    /**
     * 科技部资产数量
     */
    private int kjbZcNumber;

    /**
     * 运营部资产数量
     */
    private int yybZcNumber;

    /**
     * 保卫部资产数量
     */
    private int bwbZcNumber;

    /**
     * 资产总值
     */
    private BigDecimal zcTotalValue;

    /**
     * 本年保修数量
     */
    private int repairCount;

    /**
     * 本年处置数量
     */
    private int dealCount;


    public int getZcNumber() {
        return zcNumber;
    }

    public void setZcNumber(int zcNumber) {
        this.zcNumber = zcNumber;
    }

    public int getZhbZcNumber() {
        return zhbZcNumber;
    }

    public void setZhbZcNumber(int zhbZcNumber) {
        this.zhbZcNumber = zhbZcNumber;
    }

    public int getKjbZcNumber() {
        return kjbZcNumber;
    }

    public void setKjbZcNumber(int kjbZcNumber) {
        this.kjbZcNumber = kjbZcNumber;
    }

    public int getYybZcNumber() {
        return yybZcNumber;
    }

    public void setYybZcNumber(int yybZcNumber) {
        this.yybZcNumber = yybZcNumber;
    }

    public int getBwbZcNumber() {
        return bwbZcNumber;
    }

    public void setBwbZcNumber(int bwbZcNumber) {
        this.bwbZcNumber = bwbZcNumber;
    }

    public BigDecimal getZcTotalValue() {
        return zcTotalValue;
    }

    public void setZcTotalValue(BigDecimal zcTotalValue) {
        this.zcTotalValue = zcTotalValue;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }


    @Override
    public String toString() {
        return "IndexBranchCountVO{" +
                "zcNumber=" + zcNumber +
                ", zhbZcNumber=" + zhbZcNumber +
                ", kjbZcNumber=" + kjbZcNumber +
                ", yybZcNumber=" + yybZcNumber +
                ", bwbZcNumber=" + bwbZcNumber +
                ", zcTotalValue=" + zcTotalValue +
                ", repairCount=" + repairCount +
                ", dealCount=" + dealCount +
                '}';
    }
}
