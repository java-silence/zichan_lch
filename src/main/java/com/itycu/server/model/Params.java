package com.itycu.server.model;

import java.util.Date;

public class Params extends BaseEntity<Long> {

	private Long pid;
	private String ccode;
	private String cname;
	private String val1;
	private String val2;
	private String val3;
	private Integer isort;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String status;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;

	public Long getPid() {
		return pid;
	}
	public void  setPid(Long pid) {
		this.pid = pid;
	}
	public String getCcode() {
		return ccode;
	}
	public void  setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCname() {
		return cname;
	}
	public void  setCname(String cname) {
		this.cname = cname;
	}
	public String getVal1() {
		return val1;
	}
	public void  setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void  setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3;
	}
	public void  setVal3(String val3) {
		this.val3 = val3;
	}
	public Integer getIsort() {
		return isort;
	}
	public void  setIsort(Integer isort) {
		this.isort = isort;
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
	public String getCtype() {
		return ctype;
	}
	public void  setCtype(String ctype) {
		this.ctype = ctype;
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

}
