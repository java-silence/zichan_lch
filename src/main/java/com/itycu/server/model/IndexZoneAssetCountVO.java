package com.itycu.server.model;

/**
 * 查询首页资产数量的统计
 */
public class IndexZoneAssetCountVO {

    /**
     * 地区名称
     */
    private String zoneName;

    /**
     * 统计数据
     */
    private int dataCount;


    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Override
    public String toString() {
        return "IndexZoneAssetCountVO{" +
                "zoneName='" + zoneName + '\'' +
                ", dataCount=" + dataCount +
                '}';
    }
}
