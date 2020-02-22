package com.itycu.server.model;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 维修预算子项
 */
@Data
public class BudgetMaintainItem extends BaseEntity<Long> implements Serializable {

    /** 预算主单号. */
	private Integer budgetMaintainId;
	private Integer zcId;
	private String epcid;
	private String zcCodenum;
	private String zcName;
	private Integer zcCategoryId;
	private String specification;
	private String model;
	private Integer num;
	private BigDecimal maintainPrice;
	private BigDecimal maintainTotalPrice;
	private String maintainReason;

	/** 附加名称. */
	private String fileName;
	/** 附件相对地址. */
	private String fileUrl;

	/** 审核流程状态. */
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
