package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Vendor extends BaseEntity<Long> {

	private String ccode;
	private String cname;
	private String abbname;
	private Integer cid;
	private String venaddress;
	private String venpostcode;
	private String venregcode;
	private String venbank;
	private String venaccount;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date devdate;
	private String venperson;
	private String venphone;
	private String venfax;
	private String venemail;
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
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private String ppath;	//分类位置
	private Long deptid;

	private String classname;
	private String creator;

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
	public Integer getCid() {
		return cid;
	}
	public void  setCid(Integer cid) {
		this.cid = cid;
	}
	public String getVenaddress() {
		return venaddress;
	}
	public void  setVenaddress(String venaddress) {
		this.venaddress = venaddress;
	}
	public String getVenpostcode() {
		return venpostcode;
	}
	public void  setVenpostcode(String venpostcode) {
		this.venpostcode = venpostcode;
	}
	public String getVenregcode() {
		return venregcode;
	}
	public void  setVenregcode(String venregcode) {
		this.venregcode = venregcode;
	}
	public String getVenbank() {
		return venbank;
	}
	public void  setVenbank(String venbank) {
		this.venbank = venbank;
	}
	public String getVenaccount() {
		return venaccount;
	}
	public void  setVenaccount(String venaccount) {
		this.venaccount = venaccount;
	}
	public Date getDevdate() {
		return devdate;
	}
	public void  setDevdate(Date devdate) {
		this.devdate = devdate;
	}
	public String getVenperson() {
		return venperson;
	}
	public void  setVenperson(String venperson) {
		this.venperson = venperson;
	}
	public String getVenphone() {
		return venphone;
	}
	public void  setVenphone(String venphone) {
		this.venphone = venphone;
	}
	public String getVenfax() {
		return venfax;
	}
	public void  setVenfax(String venfax) {
		this.venfax = venfax;
	}
	public String getVenemail() {
		return venemail;
	}
	public void  setVenemail(String venemail) {
		this.venemail = venemail;
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
}
