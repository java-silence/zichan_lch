package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcBf extends BaseEntity<Long> {

	// 保存类别
	private String type;

	private Long applyUserId;
	private Long applyDeptId;
	private String zcIds;
	private String bfDes;
	private String bz;
	private Long flowid;
	private Long stepid;
	private Integer status;
	private Integer del;
	private Long createBy;
	private Long updateBy;
	private Integer bfCategory;

	private String bfNum;
	private String deptCode;

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
	public String getBfDes() {
		return bfDes;
	}
	public void  setBfDes(String bfDes) {
		this.bfDes = bfDes;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

	public Integer getBfCategory() {
		return bfCategory;
	}

	public void setBfCategory(Integer bfCategory) {
		this.bfCategory = bfCategory;
	}

    public String getBfNum() {
        return bfNum;
    }

    public void setBfNum(String bfNum) {
        this.bfNum = bfNum;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
