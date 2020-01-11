package com.itycu.server.app.vo.pandian;


import lombok.Data;

/**
 * 盘点记录中的表 盘盈和盘亏资产列表
 */
@Data
public class CheckItemReportVO {



    private int id;

    private int zcCheckId;

    private int  zcId;

    private String zcName;

    private String storeAddress;


    private String syDeptName;

    private String glDeptName;

    private String zcCodenum;

    private String epcid;

}
