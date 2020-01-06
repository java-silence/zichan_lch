package com.itycu.server.model;

import java.util.Date;

public class Warehouse extends BaseEntity<Long> {

	private String whcode;
	private String whname;
	private String whaddress;
	private String whphone;
	private Long whperson;
	private Long deptid;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String status;
	private String del;
	private String memo;
	private String biztype;
	private String c01;
	private String c02;
	private String c03;
	private String whpersonname;
	private String deptname;

	public String getWhcode() {
		return whcode;
	}
	public void  setWhcode(String whcode) {
		this.whcode = whcode;
	}
	public String getWhname() {
		return whname;
	}
	public void  setWhname(String whname) {
		this.whname = whname;
	}
	public String getWhaddress() {
		return whaddress;
	}
	public void  setWhaddress(String whaddress) {
		this.whaddress = whaddress;
	}
	public String getWhphone() {
		return whphone;
	}
	public void  setWhphone(String whphone) {
		this.whphone = whphone;
	}
	public Long getWhperson() {
		return whperson;
	}
	public void  setWhperson(Long whperson) {
		this.whperson = whperson;
	}
	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
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
	public String getStatus() {
		return status;
	}
	public void  setStatus(String status) {
		this.status = status;
	}
	public String getDel() {
		return del;
	}
	public void  setDel(String del) {
		this.del = del;
	}
	public String getMemo() {
		return memo;
	}
	public void  setMemo(String memo) {
		this.memo = memo;
	}
	public String getBiztype() {
		return biztype;
	}
	public void  setBiztype(String biztype) {
		this.biztype = biztype;
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

	public String getWhpersonname() {
		return whpersonname;
	}

	public void setWhpersonname(String whpersonname) {
		this.whpersonname = whpersonname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
}
