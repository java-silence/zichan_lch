package com.itycu.server.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Pushmsg extends BaseEntity<Long> {

	private String title;
	private String content;
	private Long userid;
	private String url;
	private Long bizid;
	private Long todoid;
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

	private String creator;
	private String username;


	public String getTitle() {
		return title;
	}
	public void  setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void  setContent(String content) {
		this.content = content;
	}
	public Long getUserid() {
		return userid;
	}
	public void  setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUrl() {
		return url;
	}
	public void  setUrl(String url) {
		this.url = url;
	}
	public Long getBizid() {
		return bizid;
	}
	public void  setBizid(Long bizid) {
		this.bizid = bizid;
	}
	public Long getTodoid() {
		return todoid;
	}
	public void  setTodoid(Long todoid) {
		this.todoid = todoid;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
