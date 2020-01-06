package com.itycu.server.model;

public class Position extends BaseEntity<Long> {

	private String positioncode;
	private String positionname;
	private String description;
	private Long deptid;
	private Long pid;
	private String status;
	private String memo;
	private String c01;
	private String c02;
	private String c03;

	public String getPositioncode() {
		return positioncode;
	}
	public String setPositioncode() {
		return positioncode;
	}
	public String getPositionname() {
		return positionname;
	}
	public String setPositionname() {
		return positionname;
	}
	public String getDescription() {
		return description;
	}
	public String setDescription() {
		return description;
	}
	public Long getDeptid() {
		return deptid;
	}
	public Long setDeptid() {
		return deptid;
	}
	public Long getPid() {
		return pid;
	}
	public Long setPid() {
		return pid;
	}
	public String getStatus() {
		return status;
	}
	public String setStatus() {
		return status;
	}
	public String getMemo() {
		return memo;
	}
	public String setMemo() {
		return memo;
	}
	public String getC01() {
		return c01;
	}
	public String setC01() {
		return c01;
	}
	public String getC02() {
		return c02;
	}
	public String setC02() {
		return c02;
	}
	public String getC03() {
		return c03;
	}
	public String setC03() {
		return c03;
	}

}
