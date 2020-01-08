package com.itycu.server.model;

public class Dept extends BaseEntity<Long> {

    /** 部门简写,大写字母. */
	private String jx;

	/** 部门全名. */
	private String allName;

	/** 部门全名. */
	private String otherAllName;

	private String deptcode;
	private String deptname;
	private Long pid;
	private String phone;
	private String address;
	private Long leader;
	private String status;
	private String del;
	/** 总行/分行/管理 */
	private String zhfhgl;
	private String memo;
	private String c01;
	private String c02;
	private String c03;
	private String ctype;

	private String suCode;

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getLeader() {
		return leader;
	}

	public void setLeader(Long leader) {
		this.leader = leader;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

    public String getZhfhgl() {
        return zhfhgl;
    }

    public void setZhfhgl(String zhfhgl) {
        this.zhfhgl = zhfhgl;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    public String getOtherAllName() {
        return otherAllName;
    }

    public void setOtherAllName(String otherAllName) {
        this.otherAllName = otherAllName;
    }

	public String getSuCode() {
		return suCode;
	}

	public void setSuCode(String suCode) {
		this.suCode = suCode;
	}
}
