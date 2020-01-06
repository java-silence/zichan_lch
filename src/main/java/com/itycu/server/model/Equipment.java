package com.itycu.server.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Equipment extends BaseEntity<Long> {

	private Long xtid;
	private Long lbid;
	private Long deptid;
	private Long xjqyid;
	private Long whqyid;
	@Excel(name = "设备名称")
	private String cname;
	private Long pid;
	private Integer isort;
	@Excel(name = "序列号")
	private String serialno;
	@Excel(name = "型号")
	private String etype;
	private Long factoryid;
	@Excel(name = "建设时间")
	private String buildtime;
	private String techphone;
	private String techname;
	private String qualityperiod;
	private Integer maintainperiod;
	private String maintainbiao1;
	private String maintainbiao2;
	private String maintainbiao3;
	private String useperiod;
	private String equipmentcontent;
	private Integer equipmentstatus;
	private Integer adminid;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date addtime;
	private Integer isxun;
	private Integer iswei;
	private String tdesc;
	private Long createby;
	private Long updateby;
	private Long auditby;
	private Date auditTime;
	@Excel(name = "设备状态")
	private String status;
	private String del;
	@Excel(name = "备注")
	private String memo;
	private String ctype;
	@Excel(name = "数量")
	private String c01;
	private String c02;
	private String c03;
	private Long whid;
	@Excel(name = "品牌")
	private String pinpai;
	private String anzhuang;
	private String zhuangfx;
	private String zhuangfw;
	private String zhuangwz;
	@Excel(name = "单价")
	private BigDecimal price;
	private Long flowid;
	private Long stepid;

	public Long getWhid() {
		return whid;
	}

	public void setWhid(Long whid) {
		this.whid = whid;
	}

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
	public String getCname() {
		return cname;
	}
	public void  setCname(String cname) {
		this.cname = cname;
	}
	public Long getPid() {
		return pid;
	}
	public void  setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getIsort() {
		return isort;
	}
	public void  setIsort(Integer isort) {
		this.isort = isort;
	}
	public String getSerialno() {
		return serialno;
	}
	public void  setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getEtype() {
		return etype;
	}
	public void  setEtype(String etype) {
		this.etype = etype;
	}
	public Long getFactoryid() {
		return factoryid;
	}
	public void  setFactoryid(Long factoryid) {
		this.factoryid = factoryid;
	}
	public String getBuildtime() {
		return buildtime;
	}
	public void  setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}
	public String getTechphone() {
		return techphone;
	}
	public void  setTechphone(String techphone) {
		this.techphone = techphone;
	}
	public String getTechname() {
		return techname;
	}
	public void  setTechname(String techname) {
		this.techname = techname;
	}
	public String getQualityperiod() {
		return qualityperiod;
	}
	public void  setQualityperiod(String qualityperiod) {
		this.qualityperiod = qualityperiod;
	}
	public Integer getMaintainperiod() {
		return maintainperiod;
	}
	public void  setMaintainperiod(Integer maintainperiod) {
		this.maintainperiod = maintainperiod;
	}
	public String getMaintainbiao1() {
		return maintainbiao1;
	}
	public void  setMaintainbiao1(String maintainbiao1) {
		this.maintainbiao1 = maintainbiao1;
	}
	public String getMaintainbiao2() {
		return maintainbiao2;
	}
	public void  setMaintainbiao2(String maintainbiao2) {
		this.maintainbiao2 = maintainbiao2;
	}
	public String getMaintainbiao3() {
		return maintainbiao3;
	}
	public void  setMaintainbiao3(String maintainbiao3) {
		this.maintainbiao3 = maintainbiao3;
	}
	public String getUseperiod() {
		return useperiod;
	}
	public void  setUseperiod(String useperiod) {
		this.useperiod = useperiod;
	}
	public String getEquipmentcontent() {
		return equipmentcontent;
	}
	public void  setEquipmentcontent(String equipmentcontent) {
		this.equipmentcontent = equipmentcontent;
	}
	public Integer getEquipmentstatus() {
		return equipmentstatus;
	}
	public void  setEquipmentstatus(Integer equipmentstatus) {
		this.equipmentstatus = equipmentstatus;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void  setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void  setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Integer getIsxun() {
		return isxun;
	}
	public void  setIsxun(Integer isxun) {
		this.isxun = isxun;
	}
	public Integer getIswei() {
		return iswei;
	}
	public void  setIswei(Integer iswei) {
		this.iswei = iswei;
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

	public String getPinpai() {
		return pinpai;
	}

	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}

	public String getAnzhuang() {
		return anzhuang;
	}

	public void setAnzhuang(String anzhuang) {
		this.anzhuang = anzhuang;
	}

	public String getZhuangfx() {
		return zhuangfx;
	}

	public void setZhuangfx(String zhuangfx) {
		this.zhuangfx = zhuangfx;
	}

	public String getZhuangfw() {
		return zhuangfw;
	}

	public void setZhuangfw(String zhuangfw) {
		this.zhuangfw = zhuangfw;
	}

	public String getZhuangwz() {
		return zhuangwz;
	}

	public void setZhuangwz(String zhuangwz) {
		this.zhuangwz = zhuangwz;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
}
