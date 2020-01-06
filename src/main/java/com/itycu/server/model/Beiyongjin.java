package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Beiyongjin extends BaseEntity<Long> {

	private String ccode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String busstype;
	private String csource;
	private Long bussid;
	private Long deptid;
	private Long clid;
	private String clbm;
	private String dizhi;
	private Long jsr;
	private BigDecimal zhichu;
	private BigDecimal shouru;
	private BigDecimal yue;
	private BigDecimal guo;
	private BigDecimal fan;
	private BigDecimal qita;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String status;// 0.普通 1.预支 2.结算 3.审核
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private String jsrname;

	private Long jsby;
	private Date jsTime;
	private String jsname;
	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
	private String deptname;
	private String statusname;

	private String auditer;

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
	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public Long getClid() {
		return clid;
	}
	public void  setClid(Long clid) {
		this.clid = clid;
	}
	public String getClbm() {
		return clbm;
	}
	public void  setClbm(String clbm) {
		this.clbm = clbm;
	}
	public String getDizhi() {
		return dizhi;
	}
	public void  setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public Long getJsr() {
		return jsr;
	}

	public void setJsr(Long jsr) {
		this.jsr = jsr;
	}

	public BigDecimal getZhichu() {
		return zhichu;
	}
	public void  setZhichu(BigDecimal zhichu) {
		this.zhichu = zhichu;
	}
	public BigDecimal getShouru() {
		return shouru;
	}
	public void  setShouru(BigDecimal shouru) {
		this.shouru = shouru;
	}
	public BigDecimal getYue() {
		return yue;
	}
	public void  setYue(BigDecimal yue) {
		this.yue = yue;
	}
	public BigDecimal getGuo() {
		return guo;
	}
	public void  setGuo(BigDecimal guo) {
		this.guo = guo;
	}
	public BigDecimal getFan() {
		return fan;
	}
	public void  setFan(BigDecimal fan) {
		this.fan = fan;
	}
	public BigDecimal getQita() {
		return qita;
	}
	public void  setQita(BigDecimal qita) {
		this.qita = qita;
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

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getJsrname() {
		return jsrname;
	}

	public void setJsrname(String jsrname) {
		this.jsrname = jsrname;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public Long getJsby() {
		return jsby;
	}

	public void setJsby(Long jsby) {
		this.jsby = jsby;
	}

	public Date getJsTime() {
		return jsTime;
	}

	public void setJsTime(Date jsTime) {
		this.jsTime = jsTime;
	}

	public String getJsname() {
		return jsname;
	}

	public void setJsname(String jsname) {
		this.jsname = jsname;
	}
}
