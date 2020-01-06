package com.itycu.server.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcCheck extends BaseEntity<Long> {

	private String  checkDeptId;
	private String checkDeptName;
	private Long checkUserId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date checkTime;
	private Integer status;
	private Integer del;
	private String bz;
	private Long createBy;
	private Long updateBy;

	private int result;
	private int total;

	private int zcCheckItemNum;


	/**
	 * 再次盘点 0 初次盘点  1是再吃盘点
	 */
	private int reCheck;



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


	private String createUserName;

	private String deptName;


	private String check_num;

	private int  normal;

	private int  error;




	private String checkUserName;

	public String getCheck_num() {
		return check_num;
	}

	public void setCheck_num(String check_num) {
		this.check_num = check_num;
	}

	public int getNormal() {
		return normal;
	}

	public void setNormal(int normal) {
		this.normal = normal;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	private List<ZcCheckItem> checkItemList;


	public List<ZcCheckItem> getCheckItemList() {
		return checkItemList;
	}

	public void setCheckItemList(List<ZcCheckItem> checkItemList) {
		this.checkItemList = checkItemList;
	}

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

	/**
	 * 盘点编号
	 * @return
	 */
 	private int bh;

	public int getBh() {
		return bh;
	}

	public void setBh(int bh) {
		this.bh = bh;
	}

	public int getReCheck() {
		return reCheck;
	}

	public void setReCheck(int reCheck) {
		this.reCheck = reCheck;
	}

	public int getZcCheckItemNum() {
		return zcCheckItemNum;
	}

	public void setZcCheckItemNum(int zcCheckItemNum) {
		this.zcCheckItemNum = zcCheckItemNum;
	}

	/**
	 * 盘点结果 1充足  2盘亏
	 * @param result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * 盘点结果 1充足  2盘亏
	 * @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * 总个数
	 * @param total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 总个数
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}






	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;

	public String getCheckDeptId() {
		return checkDeptId;
	}

	public void setCheckDeptId(String checkDeptId) {
		this.checkDeptId = checkDeptId;
	}

	public String getCheckDeptName() {
		return checkDeptName;
	}

	public void setCheckDeptName(String checkDeptName) {
		this.checkDeptName = checkDeptName;
	}

	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;


	public Long getCheckUserId() {
		return checkUserId;
	}
	public void  setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void  setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
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
	public Long getCreateBy() {
		return createBy;
	}
	public void  setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void  setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}


//	public String getDeptname() {
//		return deptname;
//	}
//	public void setDeptname(String deptname) {
//		this.deptname = deptname;
//	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ZcCheck{" +
				"checkDeptId='" + checkDeptId + '\'' +
				", checkDeptName='" + checkDeptName + '\'' +
				", checkUserId=" + checkUserId +
				", checkTime=" + checkTime +
				", status=" + status +
				", del=" + del +
				", bz='" + bz + '\'' +
				", createBy=" + createBy +
				", updateBy=" + updateBy +
				", result=" + result +
				", total=" + total +
				", zcCheckItemNum=" + zcCheckItemNum +
				", reCheck=" + reCheck +
				", zsNum='" + zsNum + '\'' +
				", zcNum='" + zcNum + '\'' +
				", kpBm='" + kpBm + '\'' +
				", profit=" + profit +
				", createUserName='" + createUserName + '\'' +
				", deptName='" + deptName + '\'' +
				", check_num='" + check_num + '\'' +
				", normal='" + normal + '\'' +
				", error='" + error + '\'' +
				", checkUserName='" + checkUserName + '\'' +
				", checkItemList=" + checkItemList +
				", bh=" + bh +
				", creator='" + creator + '\'' +
				'}';
	}
}
