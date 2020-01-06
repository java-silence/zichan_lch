package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Fapiaocg extends BaseEntity<Long> {

	private String ccode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String busstype;
	private String csource;
	private Integer bussid;
	private Integer ksid;
	private BigDecimal je;
	private BigDecimal shuilv;
	private BigDecimal shuie;
	private BigDecimal jshj;
	private String mc;
	private String nsrsbh;
	private String dzdh;
	private String khxzh;
	private BigDecimal dikou;
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
	private Integer flowid;
	private Integer stepid;
	private String ksmc;

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
	public Integer getBussid() {
		return bussid;
	}
	public void  setBussid(Integer bussid) {
		this.bussid = bussid;
	}
	public Integer getKsid() {
		return ksid;
	}
	public void  setKsid(Integer ksid) {
		this.ksid = ksid;
	}
	public BigDecimal getJe() {
		return je;
	}
	public void  setJe(BigDecimal je) {
		this.je = je;
	}
	public BigDecimal getShuilv() {
		return shuilv;
	}
	public void  setShuilv(BigDecimal shuilv) {
		this.shuilv = shuilv;
	}
	public BigDecimal getShuie() {
		return shuie;
	}
	public void  setShuie(BigDecimal shuie) {
		this.shuie = shuie;
	}
	public BigDecimal getJshj() {
		return jshj;
	}
	public void  setJshj(BigDecimal jshj) {
		this.jshj = jshj;
	}
	public String getMc() {
		return mc;
	}
	public void  setMc(String mc) {
		this.mc = mc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void  setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getDzdh() {
		return dzdh;
	}
	public void  setDzdh(String dzdh) {
		this.dzdh = dzdh;
	}
	public String getKhxzh() {
		return khxzh;
	}
	public void  setKhxzh(String khxzh) {
		this.khxzh = khxzh;
	}
	public BigDecimal getDikou() {
		return dikou;
	}
	public void  setDikou(BigDecimal dikou) {
		this.dikou = dikou;
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

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
}
