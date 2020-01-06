package com.itycu.server.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class Currstock extends BaseEntity<Long> {

	private Long whid;
	private Long deptid;
	private Long posid;
	private Long invid;
	private String cpgg;
	private Date ddate;
	private String busstype;
	private String csource;
	private Long bussid;
	private BigDecimal jian;
	private BigDecimal jianzhong;
	@Excel(name = "数量")
	private BigDecimal inum;
	@Excel(name = "单价")
	private BigDecimal ilen;
	private BigDecimal perlen;
	private BigDecimal iprice;
	private BigDecimal imoney;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String status;
	private String del;
	@Excel(name = "备注")
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private String danwei;

	private Long ksid;
	private String ksname;

	private String cbatch;

	public Long getWhid() {
		return whid;
	}
	public void  setWhid(Long whid) {
		this.whid = whid;
	}
	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public Long getPosid() {
		return posid;
	}
	public void  setPosid(Long posid) {
		this.posid = posid;
	}
	public Long getInvid() {
		return invid;
	}
	public void  setInvid(Long invid) {
		this.invid = invid;
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
	public BigDecimal getInum() {
		return inum;
	}
	public void  setInum(BigDecimal inum) {
		this.inum = inum;
	}
	public BigDecimal getIprice() {
		return iprice;
	}
	public void  setIprice(BigDecimal iprice) {
		this.iprice = iprice;
	}
	public BigDecimal getImoney() {
		return imoney;
	}
	public void  setImoney(BigDecimal imoney) {
		this.imoney = imoney;
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

	public Long getKsid() {
		return ksid;
	}

	public void setKsid(Long ksid) {
		this.ksid = ksid;
	}

	public String getKsname() {
		return ksname;
	}

	public void setKsname(String ksname) {
		this.ksname = ksname;
	}

	public String getCpgg() {
		return cpgg;
	}

	public void setCpgg(String cpgg) {
		this.cpgg = cpgg;
	}

	public BigDecimal getJian() {
		return jian;
	}

	public void setJian(BigDecimal jian) {
		this.jian = jian;
	}

	public BigDecimal getJianzhong() {
		return jianzhong;
	}

	public void setJianzhong(BigDecimal jianzhong) {
		this.jianzhong = jianzhong;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getCbatch() {
		return cbatch;
	}

	public void setCbatch(String cbatch) {
		this.cbatch = cbatch;
	}

	public BigDecimal getIlen() {
		return ilen;
	}

	public void setIlen(BigDecimal ilen) {
		this.ilen = ilen;
	}

	public BigDecimal getPerlen() {
		return perlen;
	}

	public void setPerlen(BigDecimal perlen) {
		this.perlen = perlen;
	}
}
