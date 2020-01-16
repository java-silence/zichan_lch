package com.itycu.server.model;


import lombok.Data;

import java.util.Date;

@Data
public class ZcCheckDownLoadItemVO {

    /**
     * '主键ID'
     */
    private int id;

    /**
     * '盘点主ID'
     */
    private int zcCheckId;

    /**
     * '盘点主ID'
     */
    private int zcId;

    /**
     * '盘点结果 0盘点中 1盘点完成  2盘点异常'
     */
    private int result;

    /**
     * '删除状态 0:正常 1:删除'
     */
    private int del;

    /**
     * '盘点完成时间'
     */
    private Date finishTime;

    /**
     * 管理部门
     */
    private int glDeptId;


    /**
     * 使用部门
     */
    private int syDeptId;

    /**
     * '再次盘点次数'
     */
    private int reCheck;

    /**
     * '是否盘盈 0否 1是 '
     */
    private int profit;


    /**
     * epcid
     */
    private String epcid;

}
