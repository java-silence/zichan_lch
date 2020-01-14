package com.itycu.server.app.dto.baoxiu;

import lombok.Data;

@Data
public class RepairInsertDataDTO {

    /**
     * 资产id
     */
    public long id;


    /**
     * 使用部门id
     */
    public long syDeptId;


    /**
     * 管理部门id
     */
    public long  glDeptId;


    /**
     * 图片地址
     */
    public String imageUrl;





    private String zcName;

    private String storeAddress;


    private String syDeptName;

    private String glDeptName;

    private String zcCodenum;

    private String epcid;


}
