package com.itycu.server.model;

public class Flowmember extends BaseEntity<Long> {

	private Long flowid;
	private Long stepid;
	private Long memid;
	private Integer memtype;
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

	public Long getFlowid() {
		return flowid;
	}

	public void setFlowid(Long flowid) {
		this.flowid = flowid;
	}

	public Long getStepid() {
		return stepid;
	}

	public void setStepid(Long stepid) {
		this.stepid = stepid;
	}

	public Long getMemid() {
		return memid;
	}

	public void setMemid(Long memid) {
		this.memid = memid;
	}

	public Integer getMemtype() {
		return memtype;
	}

	public void setMemtype(Integer memtype) {
		this.memtype = memtype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateby() {
		return createby;
	}

	public void setCreateby(Long createby) {
		this.createby = createby;
	}

	public Long getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getC01() {
		return c01;
	}

	public void setC01(String c01) {
		this.c01 = c01;
	}

	public String getC02() {
		return c02;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public String getC03() {
		return c03;
	}

	public void setC03(String c03) {
		this.c03 = c03;
	}
}
