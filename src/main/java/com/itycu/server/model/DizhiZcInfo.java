package com.itycu.server.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 低值易耗品导入
 */
public class DizhiZcInfo extends BaseEntity<Long> {

    @Excel(name = "" , fixedIndex = 0)
    private String cardNum;
    @Excel(name = "" , fixedIndex = 1)
    private String zcCodenum;
    @Excel(name = "" , fixedIndex = 2)
    private String zcName;
    @Excel(name = "" , fixedIndex = 3)
    private String zcCategoryCode;
    @Excel(name = "" , fixedIndex = 4)
    private String zcCategoryName;
	@Excel(name = "" , fixedIndex = 5)
	private String specification;
	@Excel(name = "" , fixedIndex = 6)
	private String model;
    //数量，用于导入复制多少数量资产
    @Excel(name = "" , fixedIndex = 7)
    private Integer quantity;
    @Excel(name = "" , fixedIndex = 8)
    private BigDecimal originalValue;
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
    @Excel(name = "" , fixedIndex = 16)
    private String glDeptName;
    @Excel(name = "" , fixedIndex = 17)
    private String syDeptName;
	@Excel(name = "" , fixedIndex = 18)
	private String syName;
	@Excel(name = "" , fixedIndex = 19)
	private String storeAddress;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "" , fixedIndex = 20, format = "yyyy-MM-dd")
	private Date startUseTime;
	@Excel(name = "" , fixedIndex = 21)
	private Integer useMonths;
	@Excel(name = "" , fixedIndex = 22)
	private Integer haveCount;
	@Excel(name = "" , fixedIndex = 23)
	private Integer remainingperiod;

    private Long zcCategoryId;
    private Long glDeptId;
    private Long syDeptId;
    private String selfCodenum;
    private Long createBy;
    private Integer del;
    private String bf;
    private Integer accountentryStatus;
    private Integer cardStatus;
    private Integer useStatus;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getZcCodenum() {
        return zcCodenum;
    }

    public void setZcCodenum(String zcCodenum) {
        this.zcCodenum = zcCodenum;
    }

    public String getZcName() {
        return zcName;
    }

    public void setZcName(String zcName) {
        this.zcName = zcName;
    }

    public String getZcCategoryCode() {
        return zcCategoryCode;
    }

    public void setZcCategoryCode(String zcCategoryCode) {
        this.zcCategoryCode = zcCategoryCode;
    }

    public String getZcCategoryName() {
        return zcCategoryName;
    }

    public void setZcCategoryName(String zcCategoryName) {
        this.zcCategoryName = zcCategoryName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public BigDecimal getLjZhejiu() {
        return ljZhejiu;
    }

    public void setLjZhejiu(BigDecimal ljZhejiu) {
        this.ljZhejiu = ljZhejiu;
    }

    public BigDecimal getBnZhejiu() {
        return bnZhejiu;
    }

    public void setBnZhejiu(BigDecimal bnZhejiu) {
        this.bnZhejiu = bnZhejiu;
    }

    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    public BigDecimal getJzzb() {
        return jzzb;
    }

    public void setJzzb(BigDecimal jzzb) {
        this.jzzb = jzzb;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public BigDecimal getNetResidualRate() {
        return netResidualRate;
    }

    public void setNetResidualRate(BigDecimal netResidualRate) {
        this.netResidualRate = netResidualRate;
    }

    public BigDecimal getNetResidualValue() {
        return netResidualValue;
    }

    public void setNetResidualValue(BigDecimal netResidualValue) {
        this.netResidualValue = netResidualValue;
    }

    public String getGlDeptName() {
        return glDeptName;
    }

    public void setGlDeptName(String glDeptName) {
        this.glDeptName = glDeptName;
    }

    public String getSyDeptName() {
        return syDeptName;
    }

    public void setSyDeptName(String syDeptName) {
        this.syDeptName = syDeptName;
    }

    public String getSyName() {
        return syName;
    }

    public void setSyName(String syName) {
        this.syName = syName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Date getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
    }

    public Integer getUseMonths() {
        return useMonths;
    }

    public void setUseMonths(Integer useMonths) {
        this.useMonths = useMonths;
    }

    public Integer getHaveCount() {
        return haveCount;
    }

    public void setHaveCount(Integer haveCount) {
        this.haveCount = haveCount;
    }

    public Integer getRemainingperiod() {
        return remainingperiod;
    }

    public void setRemainingperiod(Integer remainingperiod) {
        this.remainingperiod = remainingperiod;
    }

    public Long getZcCategoryId() {
        return zcCategoryId;
    }

    public void setZcCategoryId(Long zcCategoryId) {
        this.zcCategoryId = zcCategoryId;
    }

    public Long getGlDeptId() {
        return glDeptId;
    }

    public void setGlDeptId(Long glDeptId) {
        this.glDeptId = glDeptId;
    }

    public Long getSyDeptId() {
        return syDeptId;
    }

    public void setSyDeptId(Long syDeptId) {
        this.syDeptId = syDeptId;
    }

    public String getSelfCodenum() {
        return selfCodenum;
    }

    public void setSelfCodenum(String selfCodenum) {
        this.selfCodenum = selfCodenum;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public Integer getAccountentryStatus() {
        return accountentryStatus;
    }

    public void setAccountentryStatus(Integer accountentryStatus) {
        this.accountentryStatus = accountentryStatus;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}
