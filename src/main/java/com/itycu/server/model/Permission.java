package com.itycu.server.model;

import java.util.List;

public class Permission extends BaseEntity<Long> {

	private static final long serialVersionUID = 6180869216498363919L;

	private Long parentId;
	private String name;
	private String css;
	private String cssapp;

	private String href;
	private String hrefapp;
	private Integer type;
	private Integer typeapp;
	private String permission;
	private Integer sort;
	private String memo;
	private String del;
	private String biztype;
	private String c01;
	private String c02;
	private String c03;
	private Long createby;
	private Long updateby;

	private List<Permission> child;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Permission> getChild() {
		return child;
	}

	public void setChild(List<Permission> child) {
		this.child = child;
	}

	public String getCssapp() {
		return cssapp;
	}

	public void setCssapp(String cssapp) {
		this.cssapp = cssapp;
	}

	public String getHrefapp() {
		return hrefapp;
	}

	public void setHrefapp(String hrefapp) {
		this.hrefapp = hrefapp;
	}

	public Integer getTypeapp() {
		return typeapp;
	}

	public void setTypeapp(Integer typeapp) {
		this.typeapp = typeapp;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
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
}
