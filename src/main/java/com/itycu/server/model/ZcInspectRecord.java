package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcInspectRecord extends BaseEntity<Long> {

	private Long zcId;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date checkTime;
	private String result;
	private Long checkUserId;
	private String checkUsername;
	private Long createBy;
	private String bz;
	private Long zcInspectId;




	private String opinion;

	private String img;

	private String appearance;

	private String funct;

	public String getFunct() {
		return funct;
	}

	public void setFunct(String funct) {
		this.funct = funct;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}



	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public String getContent() {
		return content;
	}
	public void  setContent(String content) {
		this.content = content;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void  setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getResult() {
		return result;
	}
	public void  setResult(String result) {
		this.result = result;
	}
	public Long getCheckUserId() {
		return checkUserId;
	}
	public void  setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	public String getCheckUsername() {
		return checkUsername;
	}
	public void  setCheckUsername(String checkUsername) {
		this.checkUsername = checkUsername;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void  setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public Long getZcInspectId() {
		return zcInspectId;
	}
	public void  setZcInspectId(Long zcInspectId) {
		this.zcInspectId = zcInspectId;
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
