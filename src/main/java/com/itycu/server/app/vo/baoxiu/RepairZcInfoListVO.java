package com.itycu.server.app.vo.baoxiu;


import lombok.Data;

@Data
public class RepairZcInfoListVO {


    private int id;


    private String zcName;

    private String storeAddress;


    private String syDeptName;

    private String glDeptName;

    private String zcCodenum;

    private String epcid;



    /**
     * 使用部门id
     */
    public long syDeptId;


    /**
     * 管理部门id
     */
    public long  glDeptId;


}
