package com.itycu.server.app.vo.baoxiu;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ZcRepairItemVO implements Serializable {

    private long id;

    private String code;

    private String epcid;

    private String zcCodenum;

    private String zcName;

    private String glDeptName;

    private String repairDes;

    private String startUseTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    private String useMonths;

    private String originalValue;

    private String  netvalue;

    private String imgUrl;

    private Integer status;

    /** 报修结果 */
    private Integer qrStatus;


    /** 维修信息. */


}
