package com.itycu.server.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 预算购买
 */
@Data
public class BudgetBuy extends BaseEntity<Long> implements Serializable {

    /** 预算购买单号. */
	private String budgetBuyNum;

    /** 申请用户ID. */
	private Integer userId;

    /** 申请部门ID. */
	private Integer applyDeptId;

	@JsonFormat(pattern = "yyyy-MM-dd")
    /** 申请时间. */
	private Date applyTime;

    /** 流程ID. */
	private Integer flowid;

    /** 流程步骤ID. */
	private Integer stepid;

    /** 审核状态 0:初始 1:审核中 2:审核完成. */
	private Integer status;

    /** 类型 0:年度 1:月度. */
	private Integer type;

    /** 删除 0:正常 1:删除. */
	private Integer del;

}
