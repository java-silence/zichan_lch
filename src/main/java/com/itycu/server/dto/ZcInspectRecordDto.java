package com.itycu.server.dto;

import com.itycu.server.model.ZcInspect;
import com.itycu.server.model.ZcInspectRecord;

public class ZcInspectRecordDto extends ZcInspectRecord {
    private String cardNum;
    private String zcCodenum;
    private String zcName;
    private String glDeptName;
    private String syDeptName;
    private String storeAddress;
    private String inspectTime;
    private Long syDeptId;
    private String epcid;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getZcCodenum() {
        return zcCodenum;
    }

    public void setZcCodenum(String zcCodenum) {
        this.zcCodenum = zcCodenum;
    }

    public String getZcName() {
        return zcName;
    }

    public void setZcName(String zcName) {
        this.zcName = zcName;
    }

    public String getGlDeptName() {
        return glDeptName;
    }

    public void setGlDeptName(String glDeptName) {
        this.glDeptName = glDeptName;
    }

    public String getSyDeptName() {
        return syDeptName;
    }

    public void setSyDeptName(String syDeptName) {
        this.syDeptName = syDeptName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    public Long getSyDeptId() {
        return syDeptId;
    }

    public void setSyDeptId(Long syDeptId) {
        this.syDeptId = syDeptId;
    }

    public String getEpcid() {
        return epcid;
    }

    public void setEpcid(String epcid) {
        this.epcid = epcid;
    }
}
