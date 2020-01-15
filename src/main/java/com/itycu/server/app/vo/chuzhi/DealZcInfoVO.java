package com.itycu.server.app.vo.chuzhi;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class DealZcInfoVO {


    public int id;


    /**
     * 资产名称
     */
    public String zcName;

    /**
     * 放置位置
     */
    public String storeAddress;


    /**
     * 使用部门
     */
    public String syDeptName;

    /**
     * 管理部门
     */
    public String glDeptName;

    public String zcCodenum;

    public String epcid;


    /**
     * 剩余期限
     */
    private int remainingperiod;


    /**
     * 原价值
     */
    private BigDecimal originalValue;

    /**
     * 净值
     */
    private BigDecimal netvalue;

    /**
     * 使用部门id
     */
    private int syDeptId;

    /**
     * 管理部门id
     */
    private int glDeptId;



    private String bz;

    /**
     * 附件地址
     */
    private String fileName;

}
