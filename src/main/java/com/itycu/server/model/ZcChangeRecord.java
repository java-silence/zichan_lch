package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产变动记录
 */
@Data
public class ZcChangeRecord extends BaseEntity<Long> {

	private Long zcInfoId;
	private String selfCodenum;
	private String zcCodenum;
	private String zcName;
	private Long zcCategoryId;
	private String specification;
	private String model;
	private String factory;
	private String brand;
	private String supportName;
	private String supportPhone;
	private String installationFactory;
	private String depreciationTime;
	private String jcz;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date stockTime;
	private String unit;
	private String zcFrom;
	private Integer useStatus;
	private Integer accountentryStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date accountentryTime;
	private String accountantNum;
	private BigDecimal originalValue;
	private Integer cardStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date cardTime;
	private String responsible;
	private Long glDeptId;
	private Long syDeptId;
	private String syName;
	private String storeAddress;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startUseTime;
	private Integer predictYears;
	private Integer maintainCycle;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date laveTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date maintainDeadline;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lastMaintainTime;
	private BigDecimal ljZhejiu;
	private BigDecimal bnZhejiu;
	private BigDecimal netvalue;
	private BigDecimal jzzb;
	private BigDecimal net;
	private BigDecimal netResidualRate;
	private BigDecimal netResidualValue;
	private Integer useMonths;
	private Integer haveCount;
	private Integer remainingperiod;
	private String cname;
	private String venperson;
	private String venphone;
	private String venaddress;
	private String warrantyperiod;
	private Integer del;
	private String bf;
	private String bz;
	private String c01;
	private String c02;
	private String c03;
	private Long createBy;
	private Long updateBy;
	private String epcid;
	private String zcCoding;
    private String cardNum;
    private String changeField;
    /** 制单人(多表关联查询字段)*/
	private String creator;
	private Integer inspectTime;
}
