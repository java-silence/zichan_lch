package com.itycu.server.model;

public class ZcItemDeptCountInfo {


    /**
     * 部门名称
     */
    private String deptname;

    /**
     * 部门总的数量
     */
    private int deptTotalCount=0;


    /**
     * 部门实际数量
     */
    private int actCount;


    /**
     * 不同的数量
     */
    private int diffCount;


    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public int getDeptTotalCount() {
        return deptTotalCount;
    }

    public void setDeptTotalCount(int deptTotalCount) {
        this.deptTotalCount = deptTotalCount;
    }

    public int getActCount() {
        return actCount;
    }

    public void setActCount(int actCount) {
        this.actCount = actCount;
    }

    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }

    @Override
    public String toString() {
        return "ZcItemDeptCountInfo{" +
                "deptname='" + deptname + '\'' +
                ", deptTotalCount=" + deptTotalCount +
                ", actCount=" + actCount +
                ", diffCount=" + diffCount +
                '}';
    }
}
