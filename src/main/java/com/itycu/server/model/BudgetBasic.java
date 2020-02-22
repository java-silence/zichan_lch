package com.itycu.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 基础预算
 */
@Data
public class BudgetBasic extends BaseEntity<Long> implements Serializable {

    /** 基础预算单号. */
	private String buggetBasicNum;

    /** 基本工资. */
	private BigDecimal basicWage;

    /** 津贴补助. */
	private BigDecimal allowance;

    /** 奖金. */
	private BigDecimal reward;

    /** 社保. */
	private BigDecimal socialSecurity;

    /** 退休工资. */
	private BigDecimal retirementPay;

    /** 高龄补贴. */
	private BigDecimal eldAllowance;

    /** 退职费. */
	private BigDecimal retirementCosts;

    /** 抚恤金. */
	private BigDecimal pension;

    /** 生活补助. */
	private BigDecimal livingAllowance;

	private Integer userId;
	private Integer applyDeptId;

	/** 申请时间. */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date applyTime;

	private Integer flowid;
	private Integer stepid;
	private Integer flowStatus;
	private Integer step1;
	private Integer step2;
	private Integer step3;
	private Integer step4;
	private Integer status;

	/** 类型 0:年度 1:月度. */
	private Integer type;

	/** 删除 0:正常 1:删除. */
	private Integer del;

}
