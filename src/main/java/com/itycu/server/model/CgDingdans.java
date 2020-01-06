package com.itycu.server.model;

import java.math.BigDecimal;


public class CgDingdans extends BaseEntity<Long> {

	private Long pid;
	private Long invid;
	private String cpgg;
	private String danwei;
	private BigDecimal inum;
	private BigDecimal iprice;
	private BigDecimal taxrate;
	private BigDecimal itax;
	private BigDecimal imoney;
	private BigDecimal itotal;
	private String cbatch;
	private String status;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private BigDecimal dhsl;
	private BigDecimal rksl;
	private String invname;
	private BigDecimal yfdj;
	private BigDecimal yfje;

	public Long getPid() {
		return pid;
	}
	public void  setPid(Long pid) {
		this.pid = pid;
	}
	public Long getInvid() {
		return invid;
	}
	public void  setInvid(Long invid) {
		this.invid = invid;
	}
	public String getCpgg() {
		return cpgg;
	}
	public void  setCpgg(String cpgg) {
		this.cpgg = cpgg;
	}
	public String getDanwei() {
		return danwei;
	}
	public void  setDanwei(String danwei) {
		this.danwei = danwei;
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
	public BigDecimal getTaxrate() {
		return taxrate;
	}
	public void  setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}
	public BigDecimal getItax() {
		return itax;
	}
	public void  setItax(BigDecimal itax) {
		this.itax = itax;
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
	public String getCbatch() {
		return cbatch;
	}
	public void  setCbatch(String cbatch) {
		this.cbatch = cbatch;
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
	public BigDecimal getDhsl() {
		return dhsl;
	}
	public void  setDhsl(BigDecimal dhsl) {
		this.dhsl = dhsl;
	}
	public BigDecimal getRksl() {
		return rksl;
	}
	public void  setRksl(BigDecimal rksl) {
		this.rksl = rksl;
	}

	public String getInvname() {
		return invname;
	}

	public void setInvname(String invname) {
		this.invname = invname;
	}

	public BigDecimal getYfdj() {
		return yfdj;
	}

	public void setYfdj(BigDecimal yfdj) {
		this.yfdj = yfdj;
	}

	public BigDecimal getYfje() {
		return yfje;
	}

	public void setYfje(BigDecimal yfje) {
		this.yfje = yfje;
	}
}
