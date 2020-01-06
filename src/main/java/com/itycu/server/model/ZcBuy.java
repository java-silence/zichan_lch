package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcBuy extends BaseEntity<Long> {

	// 保存类别
	private String type;

	private String companyName;
	private Long syDeptId;
	private String deptCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date applyTime;
	private Long applyUserId;
	private Long glDeptId;
	private Long flowid;
	private Long stepid;
	private Integer status;
	private Integer del;
	private Long createBy;
	private Long updateBy;
    private String bz;

    private String buyNum;
    private String fileName;
    private String fileUrl;

	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public String getCompanyName() {
		return companyName;
	}
	public void  setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getSyDeptId() {
		return syDeptId;
	}
	public void  setSyDeptId(Long syDeptId) {
		this.syDeptId = syDeptId;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void  setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Long getApplyUserId() {
		return applyUserId;
	}
	public void  setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Long getGlDeptId() {
		return glDeptId;
	}
	public void  setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }
}
