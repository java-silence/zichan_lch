package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer extends BaseEntity<Long> {

	private String ccode;
	@Excel(name = "客户名称")
	private String cname;
	@Excel(name = "客户简称")
	private String abbname;
	private Long cid;
	@Excel(name = "地址")
	private String caddress;
	@Excel(name = "电话4")
	private String cpostcode;
	@Excel(name = "企业代码")
	private String cregcode;
	@Excel(name = "银行")
	private String cbank;
	@Excel(name = "账号")
	private String caccount;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date devdate;
	@Excel(name = "cperson")
	private String cperson;
	@Excel(name = "电话1")
	private String cphone;
	@Excel(name = "电话2")
	private String cfax;
	@Excel(name = "电话3")
	private String cemail;
	@Excel(name = "法人")
	private String legalperson;
	private String pic;
	private String barcode;
	private String tdesc;
	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String status;
	private String del;
	@Excel(name = "备注")
	private String memo;
	@Excel(name = "客户类型")
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	@Excel(name = "信用额度")
	private BigDecimal xinyong;
	@Excel(name = "回款周期")
	private String shuilv;
	private String ppath;	//分类位置
	private Long deptid;
	@Excel(name = "客户分类")
	private String classname;
	private String creator;
	private Long xianluid;
	private String xianlu;

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
	public String getAbbname() {
		return abbname;
	}
	public void  setAbbname(String abbname) {
		this.abbname = abbname;
	}
//	public Integer getCid() {
//		return cid;
//	}
//	public void  setCid(Integer cid) {
//		this.cid = cid;
//	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCaddress() {
		return caddress;
	}
	public void  setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCpostcode() {
		return cpostcode;
	}
	public void  setCpostcode(String cpostcode) {
		this.cpostcode = cpostcode;
	}
	public String getCregcode() {
		return cregcode;
	}
	public void  setCregcode(String cregcode) {
		this.cregcode = cregcode;
	}
	public String getCbank() {
		return cbank;
	}
	public void  setCbank(String cbank) {
		this.cbank = cbank;
	}
	public String getCaccount() {
		return caccount;
	}
	public void  setCaccount(String caccount) {
		this.caccount = caccount;
	}
	public Date getDevdate() {
		return devdate;
	}
	public void  setDevdate(Date devdate) {
		this.devdate = devdate;
	}
	public String getCperson() {
		return cperson;
	}
	public void  setCperson(String cperson) {
		this.cperson = cperson;
	}
	public String getCphone() {
		return cphone;
	}
	public void  setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCfax() {
		return cfax;
	}
	public void  setCfax(String cfax) {
		this.cfax = cfax;
	}
	public String getCemail() {
		return cemail;
	}
	public void  setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void  setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getPic() {
		return pic;
	}
	public void  setPic(String pic) {
		this.pic = pic;
	}
	public String getBarcode() {
		return barcode;
	}
	public void  setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getTdesc() {
		return tdesc;
	}
	public void  setTdesc(String tdesc) {
		this.tdesc = tdesc;
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
	public BigDecimal getXinyong() {
		return xinyong;
	}
	public void  setXinyong(BigDecimal xinyong) {
		this.xinyong = xinyong;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getShuilv() {
		return shuilv;
	}

	public void setShuilv(String shuilv) {
		this.shuilv = shuilv;
	}

	public String getPpath() {
		return ppath;
	}

	public void setPpath(String ppath) {
		this.ppath = ppath;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Long getXianluid() {
		return xianluid;
	}

	public void setXianluid(Long xianluid) {
		this.xianluid = xianluid;
	}

	public String getXianlu() {
		return xianlu;
	}

	public void setXianlu(String xianlu) {
		this.xianlu = xianlu;
	}
}
