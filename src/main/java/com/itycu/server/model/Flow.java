package com.itycu.server.model;

public class 	Flow extends BaseEntity<Long> {

	private String flowname;
	private String description;
	private Long createby;
	private Long updateby;
	private String status;
	private String del;
	private String memo;
	private String biztype;
	private String c01;
	private String c02;
	private String c03;

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreateby(Long createby) {
		this.createby = createby;
	}

	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public void setC01(String c01) {
		this.c01 = c01;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public void setC03(String c03) {
		this.c03 = c03;
	}

	public String getFlowname() {
		return flowname;
	}
	public String setFlowname() {
		return flowname;
	}
	public String getDescription() {
		return description;
	}
	public String setDescription() {
		return description;
	}
	public Long getCreateby() {
		return createby;
	}
	public Long setCreateby() {
		return createby;
	}
	public Long getUpdateby() {
		return updateby;
	}
	public Long setUpdateby() {
		return updateby;
	}
	public String getStatus() {
		return status;
	}
	public String setStatus() {
		return status;
	}
	public String getDel() {
		return del;
	}
	public String setDel() {
		return del;
	}
	public String getMemo() {
		return memo;
	}
	public String setMemo() {
		return memo;
	}
	public String getBiztype() {
		return biztype;
	}
	public String setBiztype() {
		return biztype;
	}
	public String getC01() {
		return c01;
	}
	public String setC01() {
		return c01;
	}
	public String getC02() {
		return c02;
	}
	public String setC02() {
		return c02;
	}
	public String getC03() {
		return c03;
	}
	public String setC03() {
		return c03;
	}

}
