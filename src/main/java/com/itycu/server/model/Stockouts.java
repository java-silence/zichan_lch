package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.List;


public class Stockouts extends BaseEntity<Long> {

	private Long pid;
	private Long invid;
	private String cpgg;
	private String danwei;
	private BigDecimal jian;
	private BigDecimal jianzhong;
	private BigDecimal ilen;
	private BigDecimal perlen;
	private BigDecimal inum;
	private BigDecimal iprice;
	private BigDecimal discount;
	private BigDecimal taxrate;
	private BigDecimal itax;
	private BigDecimal imoney;
	private String cbatch;
	private String status;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	private BigDecimal costprice;
	private BigDecimal costmoney;
	private BigDecimal profit;

	private Long scjlid;
	private Long zhuangcheid;
	private Long mxwhid;

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
	public BigDecimal getDiscount() {
		return discount;
	}
	public void  setDiscount(BigDecimal discount) {
		this.discount = discount;
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

	public String getCpgg() {
		return cpgg;
	}

	public void setCpgg(String cpgg) {
		this.cpgg = cpgg;
	}

	public BigDecimal getCostprice() {
		return costprice;
	}

	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}

	public BigDecimal getCostmoney() {
		return costmoney;
	}

	public void setCostmoney(BigDecimal costmoney) {
		this.costmoney = costmoney;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
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

	public Long getScjlid() {
		return scjlid;
	}

	public void setScjlid(Long scjlid) {
		this.scjlid = scjlid;
	}

	public Long getZhuangcheid() {
		return zhuangcheid;
	}

	public void setZhuangcheid(Long zhuangcheid) {
		this.zhuangcheid = zhuangcheid;
	}

	public Long getMxwhid() {
		return mxwhid;
	}

	public void setMxwhid(Long mxwhid) {
		this.mxwhid = mxwhid;
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
