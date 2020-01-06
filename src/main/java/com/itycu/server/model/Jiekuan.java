package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Jiekuan extends BaseEntity<Long> {

	private Long deptid;
	private BigDecimal jkje;
	private String jksy;
	private String status;
	private String memo;
	private String del;
	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String c01;
	private String c02;
	private String c03;
	private Integer flowid;
	private Integer stepid;


	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public BigDecimal getJkje() {
		return jkje;
	}
	public void  setJkje(BigDecimal jkje) {
		this.jkje = jkje;
	}
	public String getJksy() {
		return jksy;
	}
	public void  setJksy(String jksy) {
		this.jksy = jksy;
	}
	public String getStatus() {
		return status;
	}
	public void  setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void  setMemo(String memo) {
		this.memo = memo;
	}
	public String getDel() {
		return del;
	}
	public void  setDel(String del) {
		this.del = del;
	}
	public Long getCreateby() {
		return createby;
	}
	public void  setCreateby(Long createby) {
		this.createby = createby;
	}
	public Long getUpdateby() {
		return updateby;
	}
	public void  setUpdateby(Long updateby) {
		this.updateby = updateby;
	}
	public Long getAuditby() {
		return auditby;
	}
	public void  setAuditby(Long auditby) {
		this.auditby = auditby;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void  setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
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
	public Integer getFlowid() {
		return flowid;
	}
	public void  setFlowid(Integer flowid) {
		this.flowid = flowid;
	}
	public Integer getStepid() {
		return stepid;
	}
	public void  setStepid(Integer stepid) {
		this.stepid = stepid;
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
}
