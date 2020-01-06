package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcCategory extends BaseEntity<Long> {

	/** 资产类别 */
	private String catCode;
	private Long pid;
	private String code;
	private String name;
	private String cardStyle;
	private String accountantCode;
	private String accountantName;
	private Integer del;
	private String bz;
	private String c01;
	private String c02;
	private String c03;
	private Long createBy;
	private Long updateBy;


	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public String getCatCode() {
		return catCode;
	}
	public void  setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public Long getPid() {
		return pid;
	}
	public void  setPid(Long pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void  setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void  setName(String name) {
		this.name = name;
	}
	public String getCardStyle() {
		return cardStyle;
	}
	public void  setCardStyle(String cardStyle) {
		this.cardStyle = cardStyle;
	}
	public String getAccountantCode() {
		return accountantCode;
	}
	public void  setAccountantCode(String accountantCode) {
		this.accountantCode = accountantCode;
	}
	public String getAccountantName() {
		return accountantName;
	}
	public void  setAccountantName(String accountantName) {
		this.accountantName = accountantName;
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
}
