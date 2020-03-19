package com.itycu.server.app.vo.diaopei;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 调配资产页面VO
 */
@Data
public class DeployZcListVO {

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
     * 原价值
     */
    private BigDecimal originalValue;

    /**
     * 净值
     */
    private BigDecimal netvalue;


    /**
     * 调入部门id
     */
    private Integer backDeptId;

    /**
     * 调入部门名称
     */
    private String backUsername;

    /**
     * 使用部门id
     */
    private int syDeptId;

    /**
     * 管理部门id
     */
    private int glDeptId;

    /**
     * 调配说明
     */
    private String bz;

}
