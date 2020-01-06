package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ZxBorrow extends BaseEntity<Long> {

	private Long deptid;
	private Long whid;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bizdate;
	private Long eqpid;
	private String description;
	private BigDecimal quantity;
	private String unit;
	private BigDecimal price;
	private BigDecimal money;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expectreturn;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returndate;
	private String returndesc;
	private String status;
	private String memo;
	private String del;
	private String biztype;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	private String c01;
	private String c02;
	private String c03;
	private Long flowid;
	private Long stepid;
	private Long whid2;

	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public Long getWhid() {
		return whid;
	}
	public void  setWhid(Long whid) {
		this.whid = whid;
	}
	public Date getBizdate() {
		return bizdate;
	}
	public void  setBizdate(Date bizdate) {
		this.bizdate = bizdate;
	}
	public Long getEqpid() {
		return eqpid;
	}
	public void  setEqpid(Long eqpid) {
		this.eqpid = eqpid;
	}
	public String getDescription() {
		return description;
	}
	public void  setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void  setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void  setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void  setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void  setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getExpectreturn() {
		return expectreturn;
	}
	public void  setExpectreturn(Date expectreturn) {
		this.expectreturn = expectreturn;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void  setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public String getReturndesc() {
		return returndesc;
	}
	public void  setReturndesc(String returndesc) {
		this.returndesc = returndesc;
	}
	public String getStatus() {
		return status;
	}
	public void  setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void  setMemo(String memo) {
		this.memo = memo;
	}
	public String getDel() {
		return del;
	}
	public void  setDel(String del) {
		this.del = del;
	}
	public String getBiztype() {
		return biztype;
	}
	public void  setBiztype(String biztype) {
		this.biztype = biztype;
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

	public void setFlowid(Long flowid) {
		this.flowid = flowid;
	}

	public Long getStepid() {
		return stepid;
	}

	public void setStepid(Long stepid) {
		this.stepid = stepid;
	}

	public Long getWhid2() {
		return whid2;
	}

	public void setWhid2(Long whid2) {
		this.whid2 = whid2;
	}
}
