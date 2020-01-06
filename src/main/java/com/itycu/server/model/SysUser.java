package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysUser extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer sex;
	private Integer status;
	private String intro;
	private long deptid;
	private String clientid;
	private String openid;
	private Long positionid;

	private Long createby;
	private Long updateby;
	private Long auditby;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date auditTime;
	private String del;
	private String memo;
	private String ctype;
	private String c01;
	private String c02;
	private String c03;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rzsj;
	private String zytc;
	private BigDecimal yuexin;
	private String jjlxr;
	private String jjlxrdh;
	private String zjzp;
	private String jlfj;
	private String zzzt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lzsj;
	private String lzyy;


	private String logintitle;


	public String getLogintitle() {
		return logintitle;
	}

	public void setLogintitle(String logintitle) {
		this.logintitle = logintitle;
	}

	/**
	 * 当前登录用户的系统名称成
	 */
	private String loginUserDepartName;


	public String getLoginUserDepartName() {
		return loginUserDepartName;
	}

	public void setLoginUserDepartName(String loginUserDepartName) {
		this.loginUserDepartName = loginUserDepartName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public long getDeptid() {
		return deptid;
	}

	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Long getPositionid() {
		return positionid;
	}

	public void setPositionid(Long positionid) {
		this.positionid = positionid;
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

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
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

	public Date getRzsj() {
		return rzsj;
	}

	public void setRzsj(Date rzsj) {
		this.rzsj = rzsj;
	}

	public String getZytc() {
		return zytc;
	}

	public void setZytc(String zytc) {
		this.zytc = zytc;
	}

	public BigDecimal getYuexin() {
		return yuexin;
	}

	public void setYuexin(BigDecimal yuexin) {
		this.yuexin = yuexin;
	}

	public String getJjlxr() {
		return jjlxr;
	}

	public void setJjlxr(String jjlxr) {
		this.jjlxr = jjlxr;
	}

	public String getJjlxrdh() {
		return jjlxrdh;
	}

	public void setJjlxrdh(String jjlxrdh) {
		this.jjlxrdh = jjlxrdh;
	}

	public String getZjzp() {
		return zjzp;
	}

	public void setZjzp(String zjzp) {
		this.zjzp = zjzp;
	}

	public String getJlfj() {
		return jlfj;
	}

	public void setJlfj(String jlfj) {
		this.jlfj = jlfj;
	}

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public Date getLzsj() {
		return lzsj;
	}

	public void setLzsj(Date lzsj) {
		this.lzsj = lzsj;
	}

	public String getLzyy() {
		return lzyy;
	}

	public void setLzyy(String lzyy) {
		this.lzyy = lzyy;
	}

	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}

}
