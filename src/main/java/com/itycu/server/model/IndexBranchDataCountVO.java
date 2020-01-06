package com.itycu.server.model;

import java.math.BigDecimal;

/**
 * 数据库查询出来的数据值
 */
public class IndexBranchDataCountVO {

    /**
     *部门类型
     */
    private String  deptType;


    /**
     * 部门名称
     */
    private String  deptname;

    /**
     * 资产总值
     */
    private BigDecimal zcTotal;

    /**
     * 处置数量
     */
    private int dealCount;

    /**
     * 资产数量
     */
    private int zcCount;


    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public BigDecimal getZcTotal() {
        return zcTotal;
    }

    public void setZcTotal(BigDecimal zcTotal) {
        this.zcTotal = zcTotal;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public int getZcCount() {
        return zcCount;
    }

    public void setZcCount(int zcCount) {
        this.zcCount = zcCount;
    }


    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    @Override
    public String toString() {
        return "IndexBranchDataCountVO{" +
                "deptType=" + deptType +
                ", deptname=" + deptname +
                ", zcTotal=" + zcTotal +
                ", dealCount=" + dealCount +
                ", zcCount=" + zcCount +
                '}';
    }
}
