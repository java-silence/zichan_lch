package com.itycu.server.dto;

import com.itycu.server.model.ZcRepairItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZcRepairItemDto extends ZcRepairItem {

    private String glDeptName;
    private String cardNum;
    private String zcCodenum;
    private String zcName;
    private String zcFrom;
    private String startUseTime;
    private String remainingperiod;
    private String warrantyperiod;
    private String originalValue;
    private String netvalue;
    private String repairDes;
    private String itemStatus;
    private String flowTodoId;
    private String flowItemId;
    private String epcid;
    private String laveTime;
    private String auditor;
    private String useMonths;
    private Date repairStartTime;
    private Date repairEndTime;

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

    /** 维修单号. */
    private String code;

}
