package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Repair extends BaseEntity<Long> {

	private Long xtid;
	private Long lbid;
	private Long deptid;
	private Long xjqyid;
	private Long whqyid;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bizdate;
	private Long eqpid;
	private String eqpcode;
	private String eqpname;
	private String description;
	private String descpic;
	private String repair;
	private String repairman;
	private BigDecimal repaircost;
	private String material;
	private String status;
	private Long userid;
	private String memo;
	private String del;
	private String biztype;
	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String c01;
	private String c02;
	private String c03;
	private Long inspid;
	private Long flowid;
	private Long stepid;
	private Integer gzid;
	private String gzmc;

	public Long getXtid() {
		return xtid;
	}
	public void  setXtid(Long xtid) {
		this.xtid = xtid;
	}
	public Long getLbid() {
		return lbid;
	}
	public void  setLbid(Long lbid) {
		this.lbid = lbid;
	}
	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public Long getXjqyid() {
		return xjqyid;
	}
	public void  setXjqyid(Long xjqyid) {
		this.xjqyid = xjqyid;
	}
	public Long getWhqyid() {
		return whqyid;
	}
	public void  setWhqyid(Long whqyid) {
		this.whqyid = whqyid;
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
	public String getEqpcode() {
		return eqpcode;
	}
	public void  setEqpcode(String eqpcode) {
		this.eqpcode = eqpcode;
	}
	public String getEqpname() {
		return eqpname;
	}
	public void  setEqpname(String eqpname) {
		this.eqpname = eqpname;
	}
	public String getDescription() {
		return description;
	}
	public void  setDescription(String description) {
		this.description = description;
	}
	public String getDescpic() {
		return descpic;
	}
	public void  setDescpic(String descpic) {
		this.descpic = descpic;
	}
	public String getRepair() {
		return repair;
	}
	public void  setRepair(String repair) {
		this.repair = repair;
	}
	public String getRepairman() {
		return repairman;
	}
	public void  setRepairman(String repairman) {
		this.repairman = repairman;
	}
	public BigDecimal getRepaircost() {
		return repaircost;
	}
	public void  setRepaircost(BigDecimal repaircost) {
		this.repaircost = repaircost;
	}
	public String getMaterial() {
		return material;
	}
	public void  setMaterial(String material) {
		this.material = material;
	}
	public String getStatus() {
		return status;
	}
	public void  setStatus(String status) {
		this.status = status;
	}
	public Long getUserid() {
		return userid;
	}
	public void  setUserid(Long userid) {
		this.userid = userid;
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
	public Long getInspid() {
		return inspid;
	}
	public void  setInspid(Long inspid) {
		this.inspid = inspid;
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

	public void setStepid(Long stepid) {
		this.stepid = stepid;
	}

	public Integer getGzid() {return gzid;}
	public void setGzid(Integer gzid) {this.gzid = gzid;}

	public String getGzmc() {return gzmc;}
	public void setGzmc(String gzmc) {this.gzmc = gzmc;}
}
