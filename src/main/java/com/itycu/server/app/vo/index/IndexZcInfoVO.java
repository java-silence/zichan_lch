package com.itycu.server.app.vo.index;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IndexZcInfoVO {

    private int    id;

    private String epcid;

    private String zcName;


    private String zcCategoryName;

    private String syDeptName;

    /**
     * 资产来源
     */
    private String zcFrom;


    private String glDeptName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date  startUseTime;


    private int  remainingperiod;

    /**
     * 净额
     */
    private BigDecimal net;

    /**
     * 净值
     */
    private BigDecimal originalValue;


    private String zcCodenum;

}
