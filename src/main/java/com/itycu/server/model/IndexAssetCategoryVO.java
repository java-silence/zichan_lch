package com.itycu.server.model;

/**
 * 首页资产分类的统计数据
 */

public class IndexAssetCategoryVO {

    /**
     * 资产分离的名称
     */
    private   String name;


    /**
     * 资产分类的统计数据
     */
    private int dataCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Override
    public String toString() {
        return "IndexAssetCategoryVO{" +
                "name='" + name + '\'' +
                ", dataCount=" + dataCount +
                '}';
    }
}
