package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CgDingdan extends BaseEntity<Long> {

	private String ccode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String busstype;
	private String csource;
	private Long bussid;
	private String jglx;
	private String fkfs;
	private Long whid;
	private Long deptid;
	private Long userid;
	private Long ksid;
	private BigDecimal inum;
	private BigDecimal taxrate;
	private BigDecimal tax;
	private BigDecimal imoney;
	private BigDecimal itotal;
	private BigDecimal yfdj;
	private BigDecimal yfje;
	private Long yffkid;
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
	private BigDecimal dhsl;
	private BigDecimal dhje;
	private BigDecimal rksl;
	private BigDecimal ykfp;
	private BigDecimal yfkje;

	//以下为关联字段
	private String deptname;	//部门
	private String ksmc;        //客商名称
	private String abbname;		//客户简称
	private String statusname;  //状态名称
	private String creator;
	private String telephone;
	private String clbm;

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
	public String getJglx() {
		return jglx;
	}
	public void  setJglx(String jglx) {
		this.jglx = jglx;
	}
	public String getFkfs() {
		return fkfs;
	}
	public void  setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}
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
	public Long getUserid() {
		return userid;
	}
	public void  setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getKsid() {
		return ksid;
	}
	public void  setKsid(Long ksid) {
		this.ksid = ksid;
	}
	public BigDecimal getInum() {
		return inum;
	}
	public void  setInum(BigDecimal inum) {
		this.inum = inum;
	}
	public BigDecimal getTaxrate() {
		return taxrate;
	}
	public void  setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void  setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getImoney() {
		return imoney;
	}
	public void  setImoney(BigDecimal imoney) {
		this.imoney = imoney;
	}
	public BigDecimal getItotal() {
		return itotal;
	}
	public void  setItotal(BigDecimal itotal) {
		this.itotal = itotal;
	}
	public BigDecimal getYfdj() {
		return yfdj;
	}
	public void  setYfdj(BigDecimal yfdj) {
		this.yfdj = yfdj;
	}
	public BigDecimal getYfje() {
		return yfje;
	}
	public void  setYfje(BigDecimal yfje) {
		this.yfje = yfje;
	}
	public Long getYffkid() {
		return yffkid;
	}
	public void  setYffkid(Long yffkid) {
		this.yffkid = yffkid;
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
	public BigDecimal getDhsl() {
		return dhsl;
	}
	public void  setDhsl(BigDecimal dhsl) {
		this.dhsl = dhsl;
	}
	public BigDecimal getDhje() {
		return dhje;
	}
	public void  setDhje(BigDecimal dhje) {
		this.dhje = dhje;
	}
	public BigDecimal getRksl() {
		return rksl;
	}
	public void  setRksl(BigDecimal rksl) {
		this.rksl = rksl;
	}
	public BigDecimal getYkfp() {
		return ykfp;
	}
	public void  setYkfp(BigDecimal ykfp) {
		this.ykfp = ykfp;
	}
	public BigDecimal getYfkje() {
		return yfkje;
	}
	public void  setYfkje(BigDecimal yfkje) {
		this.yfkje = yfkje;
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

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getAbbname() {
		return abbname;
	}

	public void setAbbname(String abbname) {
		this.abbname = abbname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getClbm() {
		return clbm;
	}

	public void setClbm(String clbm) {
		this.clbm = clbm;
	}

}
