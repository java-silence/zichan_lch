package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcDeploy extends BaseEntity<Long> {

	// 提交类型
	private String type;

	private String zcIds;
	private Long applyUserId;
	private Long applyDeptId;
	private Long frontDeptId;
	private String frontAddress;
	private Long backDeptId;
	private String backAddress;
	private String description;
	private Long flowid;
	private Long stepid;
	private Integer status;
	private Integer del;
	private String bz;
	private Long createBy;
	private Long updateBy;

	private String deployNum;
	private String deptCode;

	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public String getZcIds() {
		return zcIds;
	}
	public void  setZcIds(String zcIds) {
		this.zcIds = zcIds;
	}
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
	public Long getFrontDeptId() {
		return frontDeptId;
	}
	public void  setFrontDeptId(Long frontDeptId) {
		this.frontDeptId = frontDeptId;
	}
	public String getFrontAddress() {
		return frontAddress;
	}
	public void  setFrontAddress(String frontAddress) {
		this.frontAddress = frontAddress;
	}
	public Long getBackDeptId() {
		return backDeptId;
	}
	public void  setBackDeptId(Long backDeptId) {
		this.backDeptId = backDeptId;
	}
	public String getBackAddress() {
		return backAddress;
	}
	public void  setBackAddress(String backAddress) {
		this.backAddress = backAddress;
	}
	public String getDescription() {
		return description;
	}
	public void  setDescription(String description) {
		this.description = description;
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

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
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

	public String getDeployNum() {
		return deployNum;
	}

	public void setDeployNum(String deployNum) {
		this.deployNum = deployNum;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
