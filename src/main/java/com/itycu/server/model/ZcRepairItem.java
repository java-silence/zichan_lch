package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcRepairItem extends BaseEntity<Long> {

	private Long zcRepairId;
	private Long zcId;
	private String repairDes;
	private Long applyId;
	private Long applyDeptId;
	private String repairMode;
	private String deliverMode;
	private String outCompany;
	private String outAddress;
	private String outUsername;
	private String outPhone;
	private BigDecimal outFee;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date repairTime;
	private String frontDescription;
	private String frontPics;
	private String backDescription;
	private String backPics;
	private Integer status;
	private Integer del;
	private String bz;
	private Integer shbStatus;

	/** 确认结果，0:不合格  1:合格.*/
	private Integer qrStatus;
	private Long createBy;
	private Long updateBy;
	private String repairAddress;
	private Long glDeptId;
    private String frontPicsUrl;
    private String backPicsUrl;
    private Long auditBy;
    private Date auditTime;



    /**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getZcRepairId() {
		return zcRepairId;
	}
	public void  setZcRepairId(Long zcRepairId) {
		this.zcRepairId = zcRepairId;
	}
	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public String getRepairDes() {
		return repairDes;
	}
	public void  setRepairDes(String repairDes) {
		this.repairDes = repairDes;
	}
	public Long getApplyId() {
		return applyId;
	}
	public void  setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public Long getApplyDeptId() {
		return applyDeptId;
	}
	public void  setApplyDeptId(Long applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	public String getRepairMode() {
		return repairMode;
	}
	public void  setRepairMode(String repairMode) {
		this.repairMode = repairMode;
	}
	public String getDeliverMode() {
		return deliverMode;
	}
	public void  setDeliverMode(String deliverMode) {
		this.deliverMode = deliverMode;
	}
	public String getOutCompany() {
		return outCompany;
	}
	public void  setOutCompany(String outCompany) {
		this.outCompany = outCompany;
	}
	public String getOutAddress() {
		return outAddress;
	}
	public void  setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}
	public String getOutUsername() {
		return outUsername;
	}
	public void  setOutUsername(String outUsername) {
		this.outUsername = outUsername;
	}
	public String getOutPhone() {
		return outPhone;
	}
	public void  setOutPhone(String outPhone) {
		this.outPhone = outPhone;
	}
	public BigDecimal getOutFee() {
		return outFee;
	}
	public void  setOutFee(BigDecimal outFee) {
		this.outFee = outFee;
	}
	public Date getRepairTime() {
		return repairTime;
	}
	public void  setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	public String getFrontDescription() {
		return frontDescription;
	}
	public void  setFrontDescription(String frontDescription) {
		this.frontDescription = frontDescription;
	}
	public String getFrontPics() {
		return frontPics;
	}
	public void  setFrontPics(String frontPics) {
		this.frontPics = frontPics;
	}
	public String getBackDescription() {
		return backDescription;
	}
	public void  setBackDescription(String backDescription) {
		this.backDescription = backDescription;
	}
	public String getBackPics() {
		return backPics;
	}
	public void  setBackPics(String backPics) {
		this.backPics = backPics;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public Integer getShbStatus() {
		return shbStatus;
	}
	public void  setShbStatus(Integer shbStatus) {
		this.shbStatus = shbStatus;
	}
	public Integer getQrStatus() {
		return qrStatus;
	}
	public void  setQrStatus(Integer qrStatus) {
		this.qrStatus = qrStatus;
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

	public String getRepairAddress() {
		return repairAddress;
	}

	public void setRepairAddress(String repairAddress) {
		this.repairAddress = repairAddress;
	}

	//	public String getDeptname() {
//		return deptname;
//	}
//	public void setDeptname(String deptname) {
//		this.deptname = deptname;
//	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getGlDeptId() {
		return glDeptId;
	}

	public void setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
	}

	public String getFrontPicsUrl() {
		return frontPicsUrl;
	}

	public void setFrontPicsUrl(String frontPicsUrl) {
		this.frontPicsUrl = frontPicsUrl;
	}

	public String getBackPicsUrl() {
		return backPicsUrl;
	}

	public void setBackPicsUrl(String backPicsUrl) {
		this.backPicsUrl = backPicsUrl;
	}

	public Long getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(Long auditBy) {
		this.auditBy = auditBy;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
}
