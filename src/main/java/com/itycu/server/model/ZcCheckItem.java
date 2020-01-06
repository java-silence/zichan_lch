package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class ZcCheckItem  {



	private Long zcCheckId;
	private Long zcId;
	private String result;
	private Integer del;
	private String bz;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date finishTime;

	private String epcid;


	private int  reCheck;


	/**
	 * 追溯号码
	 */
	private String zsNum;


	/**
	 * 资产编号
	 */
	private String zcNum;

	/**
	 * 卡片编码
	 */
	private String kpBm;

	/**
	 * 是否盘盈 0否  1是
	 */
	private int profit;


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	private long id;

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}


	public String getZsNum() {
		return zsNum;
	}

	public void setZsNum(String zsNum) {
		this.zsNum = zsNum;
	}

	public String getZcNum() {
		return zcNum;
	}

	public void setZcNum(String zcNum) {
		this.zcNum = zcNum;
	}

	public String getKpBm() {
		return kpBm;
	}

	public void setKpBm(String kpBm) {
		this.kpBm = kpBm;
	}

	public int getReCheck() {
		return reCheck;
	}

	public void setReCheck(int reCheck) {
		this.reCheck = reCheck;
	}
;
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSyName() {
		return syName;
	}

	public void setSyName(String syName) {
		this.syName = syName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	private String specification;
	private String model;
	private String syName;
	private String storeAddress;


	/**
	 * 制单人（多表关联查询字段）
	 */
//	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	private String zcName;

	public Long getZcCheckId() {
		return zcCheckId;
	}
	public void  setZcCheckId(Long zcCheckId) {
		this.zcCheckId = zcCheckId;
	}
	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public String getResult() {
		return result;
	}
	public void  setResult(String result) {
		this.result = result;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void  setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getZcName() {
		return zcName;
	}
	public void  setZcName(String zcName) {
		this.zcName = zcName;
	}

	public String getEpcid() {
		return epcid;
	}
	public void  setEpcid(String epcid) {
		this.epcid = epcid;
	}


//	public String getDeptname() {
//		return deptname;
//	}
//	public void setDeptname(String deptname) {
//		this.deptname = deptname;
//	}
//	public String getCreator() {
//		return creator;
//	}
//	public void setCreator(String creator) {
//		this.creator = creator;
//	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ZcCheckItem{" +
				"zcCheckId=" + zcCheckId +
				", zcId=" + zcId +
				", result='" + result + '\'' +
				", del=" + del +
				", bz='" + bz + '\'' +
				", finishTime=" + finishTime +
				", epcid='" + epcid + '\'' +
				", reCheck=" + reCheck +
				", zsNum='" + zsNum + '\'' +
				", zcNum='" + zcNum + '\'' +
				", kpBm='" + kpBm + '\'' +
				", profit=" + profit +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", id=" + id +
				", specification='" + specification + '\'' +
				", model='" + model + '\'' +
				", syName='" + syName + '\'' +
				", storeAddress='" + storeAddress + '\'' +
				", zcName='" + zcName + '\'' +
				'}';
	}
}
