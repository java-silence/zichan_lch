package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CmsNews extends BaseEntity<Long> {

	private Long permissionId;
	private String title;
	private String author;
	private String brief;
	private String content;
	private String img;
	private String imgtype;
	private Integer status;
	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String memo;
	private String biztype;
	private String c01;
	private String c02;
	private String c03;
	private Integer hits;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date eventdate;
	private String eventtime;
	private String eventlocation;

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImgtype() {
		return imgtype;
	}

	public void setImgtype(String imgtype) {
		this.imgtype = imgtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateby() {
		return createby;
	}

	public void setCreateby(Long createby) {
		this.createby = createby;
	}

	public Long getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}

	public Long getAuditby() {
		return auditby;
	}

	public void setAuditby(Long auditby) {
		this.auditby = auditby;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getC01() {
		return c01;
	}

	public void setC01(String c01) {
		this.c01 = c01;
	}

	public String getC02() {
		return c02;
	}

	public void setC02(String c02) {
		this.c02 = c02;
	}

	public String getC03() {
		return c03;
	}

	public void setC03(String c03) {
		this.c03 = c03;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public String getEventtime() {
		return eventtime;
	}

	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}

	public String getEventlocation() {
		return eventlocation;
	}

	public void setEventlocation(String eventlocation) {
		this.eventlocation = eventlocation;
	}
}
