package com.itycu.server.model;

/**
 * 资产调配子表
 */
public class ZcDeployItem extends BaseEntity<Long> {

	private Long zcDeploy;
	private Long zcId;
	private Long syDeptId;
	private Long glDeptId;
	private Long frontDeptId;
	private Long backDeptId;
	private Integer frontDeptStatus;
	private Integer backDeptStatus;
	private Integer cwbStatus;
	private Integer status;
	private String bz;
	private Integer del;

	private Long frontUser;
	private Long backUser;
	private String frontUsername;
	private String backUsername;


	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getZcDeploy() {
		return zcDeploy;
	}
	public void  setZcDeploy(Long zcDeploy) {
		this.zcDeploy = zcDeploy;
	}
	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public Long getSyDeptId() {
		return syDeptId;
	}
	public void  setSyDeptId(Long syDeptId) {
		this.syDeptId = syDeptId;
	}
	public Long getGlDeptId() {
		return glDeptId;
	}
	public void  setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
	}
	public Long getFrontDeptId() {
		return frontDeptId;
	}
	public void  setFrontDeptId(Long frontDeptId) {
		this.frontDeptId = frontDeptId;
	}
	public Long getBackDeptId() {
		return backDeptId;
	}
	public void  setBackDeptId(Long backDeptId) {
		this.backDeptId = backDeptId;
	}
	public Integer getFrontDeptStatus() {
		return frontDeptStatus;
	}
	public void  setFrontDeptStatus(Integer frontDeptStatus) {
		this.frontDeptStatus = frontDeptStatus;
	}
	public Integer getBackDeptStatus() {
		return backDeptStatus;
	}
	public void  setBackDeptStatus(Integer backDeptStatus) {
		this.backDeptStatus = backDeptStatus;
	}
	public Integer getCwbStatus() {
		return cwbStatus;
	}
	public void  setCwbStatus(Integer cwbStatus) {
		this.cwbStatus = cwbStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
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

    public Long getFrontUser() {
        return frontUser;
    }

    public void setFrontUser(Long frontUser) {
        this.frontUser = frontUser;
    }

    public Long getBackUser() {
        return backUser;
    }

    public void setBackUser(Long backUser) {
        this.backUser = backUser;
    }

    public String getFrontUsername() {
        return frontUsername;
    }

    public void setFrontUsername(String frontUsername) {
        this.frontUsername = frontUsername;
    }

    public String getBackUsername() {
        return backUsername;
    }

    public void setBackUsername(String backUsername) {
        this.backUsername = backUsername;
    }
}
