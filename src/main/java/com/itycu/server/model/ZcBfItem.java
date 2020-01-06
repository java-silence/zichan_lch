package com.itycu.server.model;

/**
 * 资产报废子项
 */
public class ZcBfItem extends BaseEntity<Long> {

	private Long zcBfId;
	private Long zcId;
	private String cwbStatus;
	private String shbStatus;
	private String bz;

	private Long syDeptId;
	private Long glDeptId;
	private Integer status;
	private Integer del;

	private String fileName;
	private String fileUrl;

	private String identifyContent;
	private String identifyFileName;
	private String identifyFileUrl;

	private String damagedFileName;
	private String damagedFileUrl;


	/** 申请人ID */
	private Long applyUserId;

	// 使用部门的编码
	private String syDeptCode;

	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;

	private String shUsername;
	private String shDeptname;
	private String cwUsername;
	private String cwDeptname;


	private String epcid;
	private String zcName;

	public Long getZcBfId() {
		return zcBfId;
	}
	public void  setZcBfId(Long zcBfId) {
		this.zcBfId = zcBfId;
	}
	public Long getZcId() {
		return zcId;
	}
	public void  setZcId(Long zcId) {
		this.zcId = zcId;
	}
	public String getCwbStatus() {
		return cwbStatus;
	}
	public void  setCwbStatus(String cwbStatus) {
		this.cwbStatus = cwbStatus;
	}
	public String getShbStatus() {
		return shbStatus;
	}
	public void  setShbStatus(String shbStatus) {
		this.shbStatus = shbStatus;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}

    public Long getSyDeptId() {
        return syDeptId;
    }

    public void setSyDeptId(Long syDeptId) {
        this.syDeptId = syDeptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

    public String getSyDeptCode() {
        return syDeptCode;
    }

    public void setSyDeptCode(String syDeptCode) {
        this.syDeptCode = syDeptCode;
    }

	public Long getGlDeptId() {
		return glDeptId;
	}

	public void setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
	}

	public Long getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getShUsername() {
        return shUsername;
    }

    public void setShUsername(String shUsername) {
        this.shUsername = shUsername;
    }

    public String getShDeptname() {
        return shDeptname;
    }

    public void setShDeptname(String shDeptname) {
        this.shDeptname = shDeptname;
    }

    public String getCwUsername() {
        return cwUsername;
    }

    public void setCwUsername(String cwUsername) {
        this.cwUsername = cwUsername;
    }

    public String getCwDeptname() {
        return cwDeptname;
    }

    public void setCwDeptname(String cwDeptname) {
        this.cwDeptname = cwDeptname;
    }

    public String getIdentifyContent() {
        return identifyContent;
    }

    public void setIdentifyContent(String identifyContent) {
        this.identifyContent = identifyContent;
    }

    public String getIdentifyFileName() {
        return identifyFileName;
    }

    public void setIdentifyFileName(String identifyFileName) {
        this.identifyFileName = identifyFileName;
    }

    public String getIdentifyFileUrl() {
        return identifyFileUrl;
    }

    public void setIdentifyFileUrl(String identifyFileUrl) {
        this.identifyFileUrl = identifyFileUrl;
    }

    public String getDamagedFileName() {
        return damagedFileName;
    }

    public void setDamagedFileName(String damagedFileName) {
        this.damagedFileName = damagedFileName;
    }

    public String getDamagedFileUrl() {
        return damagedFileUrl;
    }

    public void setDamagedFileUrl(String damagedFileUrl) {
        this.damagedFileUrl = damagedFileUrl;
    }

    public String getEpcid() {
        return epcid;
    }

    public void setEpcid(String epcid) {
        this.epcid = epcid;
    }

    public String getZcName() {
        return zcName;
    }

    public void setZcName(String zcName) {
        this.zcName = zcName;
    }
}
