package com.itycu.server.app.vo.baoxiu;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ZcRepairItemVO {



    private long id;

    private String code;

    private String epcid;


    private String zcCodenum;

    private String zcName;

    private String glDeptName;



    private String startUseTime;

    private String useMonths;

    private String originalValue;

    private String  netvalue;



}
