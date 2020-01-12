package com.itycu.server.app.vo.diaopei;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 调配资产页面VO
 */
@Data
public class DeployZcListVO {

    public int id;



    public String zcName;

    public String storeAddress;


    public String syDeptName;

    public String glDeptName;

    public String zcCodenum;

    public String epcid;


    private BigDecimal originalValue;

    private BigDecimal netvalue;



}
