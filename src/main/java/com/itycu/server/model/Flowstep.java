package com.itycu.server.model;

public class Flowstep extends BaseEntity<Long> {

	private Long flowid;
	private Long stepid;
	private String stepname;
	private String description;
	private String tofinish;
	private String flowrule;
	private String flowact;
	private Integer passnum;
	private String localalert;
	private Integer basehour;
	private Integer cyctimes;
	private String period;
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

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTofinish() {
		return tofinish;
	}

	public void setTofinish(String tofinish) {
		this.tofinish = tofinish;
	}

	public String getFlowrule() {
		return flowrule;
	}

	public void setFlowrule(String flowrule) {
		this.flowrule = flowrule;
	}

	public String getFlowact() {
		return flowact;
	}

	public void setFlowact(String flowact) {
		this.flowact = flowact;
	}

	public Integer getPassnum() {
		return passnum;
	}

	public void setPassnum(Integer passnum) {
		this.passnum = passnum;
	}

	public String getLocalalert() {
		return localalert;
	}

	public void setLocalalert(String localalert) {
		this.localalert = localalert;
	}

	public Integer getBasehour() {
		return basehour;
	}

	public void setBasehour(Integer basehour) {
		this.basehour = basehour;
	}

	public Integer getCyctimes() {
		return cyctimes;
	}

	public void setCyctimes(Integer cyctimes) {
		this.cyctimes = cyctimes;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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
