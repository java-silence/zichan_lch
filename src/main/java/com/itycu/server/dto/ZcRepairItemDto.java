package com.itycu.server.dto;

import com.itycu.server.model.ZcRepairItem;

import java.util.Date;

public class ZcRepairItemDto extends ZcRepairItem {

    private String glDeptName;
    private String cardNum;
    private String zcCodenum;
    private String zcName;
    private String zcFrom;
    private String startUseTime;
    private String remainingperiod;
    private String warrantyperiod;
    private String originalValue;
    private String netvalue;
    private String repairDes;
    private String itemStatus;
    private String flowTodoId;
    private String flowItemId;
    private String epcid;
    private String laveTime;
    private String auditor;
    private String useMonths;
    private Date repairStartTime;
    private Date repairEndTime;

    /** 维修单号. */
    private String code;

    public String getGlDeptName() {
        return glDeptName;
    }

    public void setGlDeptName(String glDeptName) {
        this.glDeptName = glDeptName;
    }

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

    public String getZcFrom() {
        return zcFrom;
    }

    public void setZcFrom(String zcFrom) {
        this.zcFrom = zcFrom;
    }

    public String getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(String startUseTime) {
        this.startUseTime = startUseTime;
    }

    public String getRemainingperiod() {
        return remainingperiod;
    }

    public void setRemainingperiod(String remainingperiod) {
        this.remainingperiod = remainingperiod;
    }

    public String getWarrantyperiod() {
        return warrantyperiod;
    }

    public void setWarrantyperiod(String warrantyperiod) {
        this.warrantyperiod = warrantyperiod;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public String getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(String netvalue) {
        this.netvalue = netvalue;
    }

    @Override
    public String getRepairDes() {
        return repairDes;
    }

    @Override
    public void setRepairDes(String repairDes) {
        this.repairDes = repairDes;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getFlowTodoId() {
        return flowTodoId;
    }

    public void setFlowTodoId(String flowTodoId) {
        this.flowTodoId = flowTodoId;
    }

    public String getFlowItemId() {
        return flowItemId;
    }

    public void setFlowItemId(String flowItemId) {
        this.flowItemId = flowItemId;
    }

    public String getEpcid() {
        return epcid;
    }

    public void setEpcid(String epcid) {
        this.epcid = epcid;
    }

    public String getLaveTime() {
        return laveTime;
    }

    public void setLaveTime(String laveTime) {
        this.laveTime = laveTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getUseMonths() {
        return useMonths;
    }

    public void setUseMonths(String useMonths) {
        this.useMonths = useMonths;
    }

    public Date getRepairStartTime() {
        return repairStartTime;
    }

    public void setRepairStartTime(Date repairStartTime) {
        this.repairStartTime = repairStartTime;
    }

    public Date getRepairEndTime() {
        return repairEndTime;
    }

    public void setRepairEndTime(Date repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
