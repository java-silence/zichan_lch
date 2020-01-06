package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Stockout extends BaseEntity<Long> {

	private String ccode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate;
	private String busstype;
	private String csource;
	private Long bussid;
	private Long whid;
	private Long deptid;
	private Long whuserid;
	private Long whid2;
	private Long deptid2;
	private Long whuserid2;
	private Long userid;
	private Long userid2;
	private Long cusid;
	private Long venid;
	private Long orderid;
	private String invoice;
	private BigDecimal taxrate;
	private BigDecimal imoney;
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
	private Long invid;
	private BigDecimal inum;
	private BigDecimal jian;
	private Long flowid;
	private Long stepid;

	private Long zhuangcheid;
	private String xsddtype;
	private BigDecimal tax;
	private BigDecimal itotal;
	private String fkfs;

	private String clbm;
	private BigDecimal yunfei;
	private BigDecimal yfdj;
	private BigDecimal discount;
	private BigDecimal ssje;
	private BigDecimal n01;
	private BigDecimal n02;
	private BigDecimal n03;

	private BigDecimal thje;
	private BigDecimal lat;
	private BigDecimal lng;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sksj;
	private String printstatus;

	private BigDecimal xianjin;
	private BigDecimal weixin;
	private BigDecimal zhifubao;

	private BigDecimal shouzhang;
	private BigDecimal qiankuan;
	private BigDecimal shaofu;
	private BigDecimal duoxiao;		//多销
	private BigDecimal kouyufu;		//扣预付款


	public BigDecimal getJian() {
		return jian;
	}

	public void setJian(BigDecimal jian) {
		this.jian = jian;
	}

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
	public Long getWhuserid() {
		return whuserid;
	}
	public void  setWhuserid(Long whuserid) {
		this.whuserid = whuserid;
	}
	public Long getWhid2() {
		return whid2;
	}
	public void  setWhid2(Long whid2) {
		this.whid2 = whid2;
	}
	public Long getDeptid2() {
		return deptid2;
	}
	public void  setDeptid2(Long deptid2) {
		this.deptid2 = deptid2;
	}
	public Long getWhuserid2() {
		return whuserid2;
	}
	public void  setWhuserid2(Long whuserid2) {
		this.whuserid2 = whuserid2;
	}
	public Long getUserid() {
		return userid;
	}
	public void  setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getUserid2() {
		return userid2;
	}
	public void  setUserid2(Long userid2) {
		this.userid2 = userid2;
	}
	public Long getCusid() {
		return cusid;
	}
	public void  setCusid(Long cusid) {
		this.cusid = cusid;
	}
	public Long getVenid() {
		return venid;
	}
	public void  setVenid(Long venid) {
		this.venid = venid;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void  setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public String getInvoice() {
		return invoice;
	}
	public void  setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public BigDecimal getTaxrate() {
		return taxrate;
	}
	public void  setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
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

	public Long getFlowid() {
		return flowid;
	}

	public void setFlowid(Long flowid) {
		this.flowid = flowid;
	}

	public Long getStepid() {
		return stepid;
	}

	public void setStepid(Long stepid) {
		this.stepid = stepid;
	}

	public Long getZhuangcheid() {
		return zhuangcheid;
	}

	public void setZhuangcheid(Long zhuangcheid) {
		this.zhuangcheid = zhuangcheid;
	}

	public String getXsddtype() {
		return xsddtype;
	}

	public void setXsddtype(String xsddtype) {
		this.xsddtype = xsddtype;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getItotal() {
		return itotal;
	}

	public void setItotal(BigDecimal itotal) {
		this.itotal = itotal;
	}

	public String getFkfs() {
		return fkfs;
	}

	public void setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}

	public String getClbm() {
		return clbm;
	}

	public void setClbm(String clbm) {
		this.clbm = clbm;
	}

	public BigDecimal getYunfei() {
		return yunfei;
	}

	public void setYunfei(BigDecimal yunfei) {
		this.yunfei = yunfei;
	}

	public BigDecimal getYfdj() {
		return yfdj;
	}

	public void setYfdj(BigDecimal yfdj) {
		this.yfdj = yfdj;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getSsje() {
		return ssje;
	}

	public void setSsje(BigDecimal ssje) {
		this.ssje = ssje;
	}

	public BigDecimal getN01() {
		return n01;
	}

	public void setN01(BigDecimal n01) {
		this.n01 = n01;
	}

	public BigDecimal getN02() {
		return n02;
	}

	public void setN02(BigDecimal n02) {
		this.n02 = n02;
	}

	public BigDecimal getN03() {
		return n03;
	}

	public void setN03(BigDecimal n03) {
		this.n03 = n03;
	}

	public BigDecimal getThje() {
		return thje;
	}

	public void setThje(BigDecimal thje) {
		this.thje = thje;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public Date getSksj() {
		return sksj;
	}

	public void setSksj(Date sksj) {
		this.sksj = sksj;
	}

	public String getPrintstatus() {
		return printstatus;
	}

	public void setPrintstatus(String printstatus) {
		this.printstatus = printstatus;
	}

	public BigDecimal getXianjin() {
		return xianjin;
	}

	public void setXianjin(BigDecimal xianjin) {
		this.xianjin = xianjin;
	}

	public BigDecimal getWeixin() {
		return weixin;
	}

	public void setWeixin(BigDecimal weixin) {
		this.weixin = weixin;
	}

	public BigDecimal getZhifubao() {
		return zhifubao;
	}

	public void setZhifubao(BigDecimal zhifubao) {
		this.zhifubao = zhifubao;
	}

	public BigDecimal getShouzhang() {
		return shouzhang;
	}

	public void setShouzhang(BigDecimal shouzhang) {
		this.shouzhang = shouzhang;
	}

	public BigDecimal getQiankuan() {
		return qiankuan;
	}

	public void setQiankuan(BigDecimal qiankuan) {
		this.qiankuan = qiankuan;
	}

	public BigDecimal getShaofu() {
		return shaofu;
	}

	public void setShaofu(BigDecimal shaofu) {
		this.shaofu = shaofu;
	}

	public BigDecimal getDuoxiao() {
		return duoxiao;
	}

	public void setDuoxiao(BigDecimal duoxiao) {
		this.duoxiao = duoxiao;
	}

	public BigDecimal getKouyufu() {
		return kouyufu;
	}

	public void setKouyufu(BigDecimal kouyufu) {
		this.kouyufu = kouyufu;
	}
}
