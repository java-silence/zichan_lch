package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcInspect extends BaseEntity<Long> {

	private Long zcId;
	private String days;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lastCheckTime;
	private Integer del;
	private String bz;
	private Long createBy;
	private Long updateBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date checkTime;
	private Long checkUserId;
	private String checkUsername;
	private String status;
	private String code;
	private Long checkDeptId;
    private String checkDeptName;

	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public String getDays() {
		return days;
	}
	public void  setDays(String days) {
		this.days = days;
	}
	public Date getLastCheckTime() {
		return lastCheckTime;
	}
	public void  setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
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
	public void  setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void  setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void  setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Long getCheckUserId() {
		return checkUserId;
	}
	public void  setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	public String getCheckUsername() {
		return checkUsername;
	}
	public void  setCheckUsername(String checkUsername) {
		this.checkUsername = checkUsername;
	}
	public String getStatus() {
		return status;
	}
	public void  setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void  setCode(String code) {
		this.code = code;
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

	public Long getCheckDeptId() {
		return checkDeptId;
	}

	public void setCheckDeptId(Long checkDeptId) {
		this.checkDeptId = checkDeptId;
	}

	public String getCheckDeptName() {
		return checkDeptName;
	}

	public void setCheckDeptName(String checkDeptName) {
		this.checkDeptName = checkDeptName;
	}
}
