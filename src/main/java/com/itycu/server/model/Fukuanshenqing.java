package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Fukuanshenqing extends BaseEntity<Long> {

	private String ccode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String busstype;
	private String csource;
	private Long bussid;
	private String fkfs;
	private String fkxm;
	private BigDecimal fkje;
	private Long ksid;

	private Long deptid;

	private Long createby;

	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String status;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private Long flowid;
	private Long stepid;

	private String ksmc;
	private String deptname;
	private String creator;
	private String auditor;

	private String biztype;
	private List<String> fileIds;

	private String statusname;

	public String getCcode() {
		return ccode;
	}
	public void  setCcode(String ccode) {
		this.ccode = ccode;
	}
	public Date getDdate() {
		return ddate;
	}
	public void  setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public String getBusstype() {
		return busstype;
	}
	public void  setBusstype(String busstype) {
		this.busstype = busstype;
	}
	public String getCsource() {
		return csource;
	}
	public void  setCsource(String csource) {
		this.csource = csource;
	}
	public Long getBussid() {
		return bussid;
	}
	public void  setBussid(Long bussid) {
		this.bussid = bussid;
	}
	public String getFkfs() {
		return fkfs;
	}
	public void  setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}
	public String getFkxm() {
		return fkxm;
	}
	public void  setFkxm(String fkxm) {
		this.fkxm = fkxm;
	}
	public BigDecimal getFkje() {
		return fkje;
	}
	public void  setFkje(BigDecimal fkje) {
		this.fkje = fkje;
	}
	public Long getKsid() {
		return ksid;
	}
	public void  setKsid(Long ksid) {
		this.ksid = ksid;
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

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
}
