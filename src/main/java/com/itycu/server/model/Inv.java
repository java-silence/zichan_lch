package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;

public class Inv extends BaseEntity<Long> {

	private Long xtid;
	private String invcode;
	private String invname;
	private String invabbname;
	private String invstd;
	private Long invcid;
	private Long positionid;
	private BigDecimal iweight;
	private BigDecimal ivolume;
	private BigDecimal iprice;
	private BigDecimal viprice;
	private BigDecimal icost;
	private BigDecimal safenum;
	private BigDecimal topnum;
	private BigDecimal lownum;
	private String unit1;
	private Long unit2;
	private String pic;
	private String barcode;
	private Integer pid;
	private Long bomid;
	private String tdesc;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String status;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;

	public Long getXtid() {
		return xtid;
	}
	public void  setXtid(Long xtid) {
		this.xtid = xtid;
	}
	public String getInvcode() {
		return invcode;
	}
	public void  setInvcode(String invcode) {
		this.invcode = invcode;
	}
	public String getInvname() {
		return invname;
	}
	public void  setInvname(String invname) {
		this.invname = invname;
	}
	public String getInvabbname() {
		return invabbname;
	}
	public void  setInvabbname(String invabbname) {
		this.invabbname = invabbname;
	}
	public String getInvstd() {
		return invstd;
	}
	public void  setInvstd(String invstd) {
		this.invstd = invstd;
	}
	public Long getInvcid() {
		return invcid;
	}
	public void  setInvcid(Long invcid) {
		this.invcid = invcid;
	}
	public Long getPositionid() {
		return positionid;
	}
	public void  setPositionid(Long positionid) {
		this.positionid = positionid;
	}
	public BigDecimal getIweight() {
		return iweight;
	}
	public void  setIweight(BigDecimal iweight) {
		this.iweight = iweight;
	}
	public BigDecimal getIvolume() {
		return ivolume;
	}
	public void  setIvolume(BigDecimal ivolume) {
		this.ivolume = ivolume;
	}
	public BigDecimal getIprice() {
		return iprice;
	}
	public void  setIprice(BigDecimal iprice) {
		this.iprice = iprice;
	}
	public BigDecimal getViprice() {
		return viprice;
	}
	public void  setViprice(BigDecimal viprice) {
		this.viprice = viprice;
	}
	public BigDecimal getIcost() {
		return icost;
	}
	public void  setIcost(BigDecimal icost) {
		this.icost = icost;
	}
	public BigDecimal getSafenum() {
		return safenum;
	}
	public void  setSafenum(BigDecimal safenum) {
		this.safenum = safenum;
	}
	public BigDecimal getTopnum() {
		return topnum;
	}
	public void  setTopnum(BigDecimal topnum) {
		this.topnum = topnum;
	}
	public BigDecimal getLownum() {
		return lownum;
	}
	public void  setLownum(BigDecimal lownum) {
		this.lownum = lownum;
	}

	public String getUnit1() {
		return unit1;
	}

	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}

	public Long getUnit2() {
		return unit2;
	}
	public void  setUnit2(Long unit2) {
		this.unit2 = unit2;
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
	public Integer getPid() {
		return pid;
	}
	public void  setPid(Integer pid) {
		this.pid = pid;
	}
	public Long getBomid() {
		return bomid;
	}
	public void  setBomid(Long bomid) {
		this.bomid = bomid;
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

}
