package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FlowDoc extends BaseEntity<Long> {

	private String title;
	private String author;
	private String brief;
	private String content;
	private String img;
	private String imgtype;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate1;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddate2;
	private Integer status;
	private Long deptid;
	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String memo;
	private String biztype;
	private String doctype;
	private String c01;
	private String c02;
	private String c03;
	private String c04;
	private String c05;
	private Long flowid;
	private Long stepid;
	private String deptname;
	private String stepname;
	private String creator;

	public String getTitle() {
		return title;
	}
	public void  setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void  setAuthor(String author) {
		this.author = author;
	}
	public String getBrief() {
		return brief;
	}
	public void  setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void  setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void  setImg(String img) {
		this.img = img;
	}
	public String getImgtype() {
		return imgtype;
	}
	public void  setImgtype(String imgtype) {
		this.imgtype = imgtype;
	}
	public Date getDdate1() {
		return ddate1;
	}
	public void  setDdate1(Date ddate1) {
		this.ddate1 = ddate1;
	}
	public Date getDdate2() {
		return ddate2;
	}
	public void  setDdate2(Date ddate2) {
		this.ddate2 = ddate2;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
	}
	public Long getDeptid() {
		return deptid;
	}
	public void  setDeptid(Long deptid) {
		this.deptid = deptid;
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
	public String getMemo() {
		return memo;
	}
	public void  setMemo(String memo) {
		this.memo = memo;
	}
	public String getBiztype() {
		return biztype;
	}
	public void  setBiztype(String biztype) {
		this.biztype = biztype;
	}
	public String getDoctype() {
		return doctype;
	}
	public void  setDoctype(String doctype) {
		this.doctype = doctype;
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
	public String getC04() {
		return c04;
	}
	public void  setC04(String c04) {
		this.c04 = c04;
	}
	public String getC05() {
		return c05;
	}
	public void  setC05(String c05) {
		this.c05 = c05;
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
	public void  setStepid(Long stepid) {
		this.stepid = stepid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
}
