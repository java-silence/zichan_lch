package com.itycu.server.model;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 预算购买子项
 */
@Data
public class BudgetBuyItem extends BaseEntity<Long> implements Serializable {

    /** 预算购买主单号. */
    private Long budgetBuyId;
	private String zcName;
	private Integer zcCategoryId;
	private String brand;
	private String specification;
	private String model;
	private Integer num;
	private BigDecimal price;
	private BigDecimal totalprice;
	private String useDes;
	private String fileName;
	private String fileUrl;
	private Integer step1;
	private Integer step2;
	private Integer step3;
	private Integer step4;
	private Integer status;
	private Integer type;
	private Integer del;

}
