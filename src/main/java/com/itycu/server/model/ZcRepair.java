package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcRepair extends BaseEntity<Long> {

	private Long applyUserId;
	private Long applyDeptId;
	private String zcIds;
	private String repairDes;
	private String bz;
	private Long flowid;
	private Long stepid;
	private Integer status;
	private Integer repairCategory;
	private Integer del;
	private Long createBy;
	private Long updateBy;
    private String type;
	private String code;
	private String deptCode;
    private Date confirmTime;
	private Long confirmBy;
	private Long confirmDept;
	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getApplyUserId() {
		return applyUserId;
	}
	public void  setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Long getApplyDeptId() {
		return applyDeptId;
	}
	public void  setApplyDeptId(Long applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	public String getZcIds() {
		return zcIds;
	}
	public void  setZcIds(String zcIds) {
		this.zcIds = zcIds;
	}
	public String getRepairDes() {
		return repairDes;
	}
	public void  setRepairDes(String repairDes) {
		this.repairDes = repairDes;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public Long getFlowid() {
		return flowid;
	}
	public void  setFlowid(Long flowid) {
		this.flowid = flowid;
	}
	public Long getStepid() {
		return stepid;
	}
	public void  setStepid(Long stepid) {
		this.stepid = stepid;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRepairCategory() {
		return repairCategory;
	}
	public void  setRepairCategory(Integer repairCategory) {
		this.repairCategory = repairCategory;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Long getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(Long confirmBy) {
		this.confirmBy = confirmBy;
	}

	public Long getConfirmDept() {
		return confirmDept;
	}

	public void setConfirmDept(Long confirmDept) {
		this.confirmDept = confirmDept;
	}
}
