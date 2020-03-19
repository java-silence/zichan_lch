package com.itycu.server.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ZcInfo extends BaseEntity<Long> {

	private String selfCodenum;
	@Excel(name = "" , fixedIndex = 1)
	private String zcCodenum;
	@Excel(name = "" , fixedIndex = 2)
	private String zcName;
	private Long zcCategoryId;
	@Excel(name = "" , fixedIndex = 5)
	private String specification;
	@Excel(name = "" , fixedIndex = 6)
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
	@Excel(name = "单位")
	private String unit;
	@Excel(name = "资产来源")
	private String zcFrom;
	private Integer useStatus;
	private Integer accountentryStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date accountentryTime;
	private String accountantNum;
	@Excel(name = "" , fixedIndex = 8)
	private BigDecimal originalValue;
	@Excel(name = "" , fixedIndex = 0)
	private String cardNum;
	private Integer cardStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date cardTime;
	private String responsible;
	private Long glDeptId;
	private Long syDeptId;
	@Excel(name = "" , fixedIndex = 18)
	private String syName;
	@Excel(name = "" , fixedIndex = 19)
	private String storeAddress;
	@Excel(name = "" , fixedIndex = 20)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startUseTime;
	private Integer predictYears;
	@Excel(name = "维护周期/天")
	private Integer maintainCycle;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date laveTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date maintainDeadline;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lastMaintainTime;
	@Excel(name = "" , fixedIndex = 9)
	private BigDecimal ljZhejiu;
	@Excel(name = "" , fixedIndex = 10)
	private BigDecimal bnZhejiu;
	@Excel(name = "" , fixedIndex = 11)
	private BigDecimal netvalue;
	@Excel(name = "" , fixedIndex = 12)
	private BigDecimal jzzb;
	@Excel(name = "" , fixedIndex = 13)
	private BigDecimal net;
	@Excel(name = "" , fixedIndex = 14)
	private BigDecimal netResidualRate;
	@Excel(name = "" , fixedIndex = 15)
	private BigDecimal netResidualValue;
	@Excel(name = "" , fixedIndex = 21)
	private Integer useMonths;
	@Excel(name = "" , fixedIndex = 22)
	private Integer haveCount;
	@Excel(name = "" , fixedIndex = 23)
	private Integer remainingperiod;
	@Excel(name = "服务商名称")
	private String cname;
	@Excel(name = "联系人")
	private String venperson;
	@Excel(name = "联系方式")
	private String venphone;
	@Excel(name = "服务商地址")
	private String venaddress;
	@Excel(name = "保修期限")
	private String warrantyperiod;
	private Integer del;
	private String bf;
	@Excel(name = "备注")
	private String bz;
	private String c01;
	private String c02;
	private String c03;
	private Long createBy;
	private Long updateBy;
    private Integer inspectTime;
    private String epcid;
    private String zcCoding;
    /** 资产使用人 */
    private Long userId;
    private Integer catType;


	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public String getSelfCodenum() {
		return selfCodenum;
	}
	public void  setSelfCodenum(String selfCodenum) {
		this.selfCodenum = selfCodenum;
	}
	public String getZcCodenum() {
		return zcCodenum;
	}
	public void  setZcCodenum(String zcCodenum) {
		this.zcCodenum = zcCodenum;
	}
	public String getZcName() {
		return zcName;
	}
	public void  setZcName(String zcName) {
		this.zcName = zcName;
	}
	public Long getZcCategoryId() {
		return zcCategoryId;
	}
	public void  setZcCategoryId(Long zcCategoryId) {
		this.zcCategoryId = zcCategoryId;
	}
	public String getSpecification() {
		return specification;
	}
	public void  setSpecification(String specification) {
		this.specification = specification;
	}
	public String getModel() {
		return model;
	}
	public void  setModel(String model) {
		this.model = model;
	}
	public String getFactory() {
		return factory;
	}
	public void  setFactory(String factory) {
		this.factory = factory;
	}
	public String getBrand() {
		return brand;
	}
	public void  setBrand(String brand) {
		this.brand = brand;
	}
	public String getSupportName() {
		return supportName;
	}
	public void  setSupportName(String supportName) {
		this.supportName = supportName;
	}
	public String getSupportPhone() {
		return supportPhone;
	}
	public void  setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}
	public String getInstallationFactory() {
		return installationFactory;
	}
	public void  setInstallationFactory(String installationFactory) {
		this.installationFactory = installationFactory;
	}
	public String getDepreciationTime() {
		return depreciationTime;
	}
	public void  setDepreciationTime(String depreciationTime) {
		this.depreciationTime = depreciationTime;
	}
	public String getJcz() {
		return jcz;
	}
	public void  setJcz(String jcz) {
		this.jcz = jcz;
	}
	public Date getStockTime() {
		return stockTime;
	}
	public void  setStockTime(Date stockTime) {
		this.stockTime = stockTime;
	}
	public String getUnit() {
		return unit;
	}
	public void  setUnit(String unit) {
		this.unit = unit;
	}
	public String getZcFrom() {
		return zcFrom;
	}
	public void  setZcFrom(String zcFrom) {
		this.zcFrom = zcFrom;
	}
	public Integer getUseStatus() {
		return useStatus;
	}
	public void  setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	public Integer getAccountentryStatus() {
		return accountentryStatus;
	}
	public void  setAccountentryStatus(Integer accountentryStatus) {
		this.accountentryStatus = accountentryStatus;
	}
	public Date getAccountentryTime() {
		return accountentryTime;
	}
	public void  setAccountentryTime(Date accountentryTime) {
		this.accountentryTime = accountentryTime;
	}
	public String getAccountantNum() {
		return accountantNum;
	}
	public void  setAccountantNum(String accountantNum) {
		this.accountantNum = accountantNum;
	}
	public BigDecimal getOriginalValue() {
		return originalValue;
	}
	public void  setOriginalValue(BigDecimal originalValue) {
		this.originalValue = originalValue;
	}
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void  setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
	public Date getCardTime() {
		return cardTime;
	}
	public void  setCardTime(Date cardTime) {
		this.cardTime = cardTime;
	}
	public String getResponsible() {
		return responsible;
	}
	public void  setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public Long getGlDeptId() {
		return glDeptId;
	}
	public void  setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
	}
	public Long getSyDeptId() {
		return syDeptId;
	}
	public void  setSyDeptId(Long syDeptId) {
		this.syDeptId = syDeptId;
	}
	public String getSyName() {
		return syName;
	}
	public void  setSyName(String syName) {
		this.syName = syName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void  setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Date getStartUseTime() {
		return startUseTime;
	}
	public void  setStartUseTime(Date startUseTime) {
		this.startUseTime = startUseTime;
	}
	public Integer getPredictYears() {
		return predictYears;
	}
	public void  setPredictYears(Integer predictYears) {
		this.predictYears = predictYears;
	}
	public Integer getMaintainCycle() {
		return maintainCycle;
	}
	public void  setMaintainCycle(Integer maintainCycle) {
		this.maintainCycle = maintainCycle;
	}
	public Date getLaveTime() {
		return laveTime;
	}
	public void  setLaveTime(Date laveTime) {
		this.laveTime = laveTime;
	}
	public Date getMaintainDeadline() {
		return maintainDeadline;
	}
	public void  setMaintainDeadline(Date maintainDeadline) {
		this.maintainDeadline = maintainDeadline;
	}
	public Date getLastMaintainTime() {
		return lastMaintainTime;
	}
	public void  setLastMaintainTime(Date lastMaintainTime) {
		this.lastMaintainTime = lastMaintainTime;
	}
	public BigDecimal getLjZhejiu() {
		return ljZhejiu;
	}
	public void  setLjZhejiu(BigDecimal ljZhejiu) {
		this.ljZhejiu = ljZhejiu;
	}
	public BigDecimal getBnZhejiu() {
		return bnZhejiu;
	}
	public void  setBnZhejiu(BigDecimal bnZhejiu) {
		this.bnZhejiu = bnZhejiu;
	}
	public BigDecimal getNetvalue() {
		return netvalue;
	}
	public void  setNetvalue(BigDecimal netvalue) {
		this.netvalue = netvalue;
	}
	public BigDecimal getJzzb() {
		return jzzb;
	}
	public void  setJzzb(BigDecimal jzzb) {
		this.jzzb = jzzb;
	}
	public BigDecimal getNet() {
		return net;
	}
	public void  setNet(BigDecimal net) {
		this.net = net;
	}
	public BigDecimal getNetResidualRate() {
		return netResidualRate;
	}
	public void  setNetResidualRate(BigDecimal netResidualRate) {
		this.netResidualRate = netResidualRate;
	}
	public BigDecimal getNetResidualValue() {
		return netResidualValue;
	}
	public void  setNetResidualValue(BigDecimal netResidualValue) {
		this.netResidualValue = netResidualValue;
	}
	public Integer getUseMonths() {
		return useMonths;
	}
	public void  setUseMonths(Integer useMonths) {
		this.useMonths = useMonths;
	}
	public Integer getHaveCount() {
		return haveCount;
	}
	public void  setHaveCount(Integer haveCount) {
		this.haveCount = haveCount;
	}
	public Integer getRemainingperiod() {
		return remainingperiod;
	}
	public void  setRemainingperiod(Integer remainingperiod) {
		this.remainingperiod = remainingperiod;
	}
	public String getCname() {
		return cname;
	}
	public void  setCname(String cname) {
		this.cname = cname;
	}
	public String getVenperson() {
		return venperson;
	}
	public void  setVenperson(String venperson) {
		this.venperson = venperson;
	}
	public String getVenphone() {
		return venphone;
	}
	public void  setVenphone(String venphone) {
		this.venphone = venphone;
	}
	public String getVenaddress() {
		return venaddress;
	}
	public void  setVenaddress(String venaddress) {
		this.venaddress = venaddress;
	}
	public String getWarrantyperiod() {
		return warrantyperiod;
	}
	public void  setWarrantyperiod(String warrantyperiod) {
		this.warrantyperiod = warrantyperiod;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
	}
	public String getBf() {
		return bf;
	}
	public void  setBf(String bf) {
		this.bf = bf;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public String getC01() {
		return c01;
	}
	public void  setC01(String c01) {
		this.c01 = c01;
	}
	public String getC02() {
		return c02;
	}
	public void  setC02(String c02) {
		this.c02 = c02;
	}
	public String getC03() {
		return c03;
	}
	public void  setC03(String c03) {
		this.c03 = c03;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void  setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void  setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getInspectTime() {
		return inspectTime;
	}

	public void setInspectTime(Integer inspectTime) {
		this.inspectTime = inspectTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEpcid() {
		return epcid;
	}

	public void setEpcid(String epcid) {
		this.epcid = epcid;
	}

	public String getZcCoding() {
		return zcCoding;
	}

	public void setZcCoding(String zcCoding) {
		this.zcCoding = zcCoding;
	}

	public Integer getCatType() {
		return catType;
	}

	public void setCatType(Integer catType) {
		this.catType = catType;
	}
}
