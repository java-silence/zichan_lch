package com.itycu.server.app.vo.baoxiu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
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

    private String originalValue;

    private String  netvalue;

    private String imgUrl;

    private Integer status;

    /** 报修结果 */
    private Integer qrStatus;

    /** 维修信息. */
    // 剩余期限
    private String remainingperiod;

    // 到期期限
    private String useMonths;

    // 报修期限
    private String warrantyperiod;

    // 本地 委外
    private String repairMode;

    // 上门 送修
    private String deliverMode;

    // 服务商名称
    private String outCompany;

    // 服务商地址
    private String outAddress;

    // 维修联系人
    private String outUsername;

    // 维修电话
    private String outPhone;

    // 送修地址
    private String repairAddress;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
