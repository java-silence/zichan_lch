package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)实体类
 *
 * @author makejava
 * @since 2020-03-18 09:55:10
 */
@Data
public class BudgetData implements Serializable {
    private static final long serialVersionUID = 643027673464907098L;


    private int  id;

    /** 预算购买单号. */
    private String budgetDataId;

    /** 申请用户ID. */
    private int userId;

    /** 申请部门ID. */
    private Integer applyDeptId;

    /** 申请时间. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyTime;

    /** 流程ID. */
    private Integer flowid;

    /** 流程步骤ID. */
    private Integer stepid;

    /** 审核状态 0:初始 1:审核中 2:审核完成. */
    private Integer status;

    /** 类型 0:年度 1:月度. */
    private Integer budgetKind;

    /** 删除 0:正常 1:删除. */
    private Integer del;


    private List<BudgetDataItem> budgetDataList;

}