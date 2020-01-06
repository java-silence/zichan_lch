package com.itycu.server.dto;

import com.itycu.server.model.ZxBorrow;
import com.itycu.server.model.ZxBorrows;

import java.util.List;

/**
 * Created by fanlinglong on 2018/12/22.
 */
public class ZxBorrowVO extends ZxBorrow {
    private List<ZxBorrows> zxBorrowsList;

    private String deptname;
    private String whname;
    private String creator;
    private String auditor;
    private String eqpname;
    private String serialno;
    private String stepname;
    private String etype;
    private String eqpstatus;
    private List<Long> eqpIds;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getWhname() {
        return whname;
    }

    public void setWhname(String whname) {
        this.whname = whname;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getEqpname() {
        return eqpname;
    }

    public void setEqpname(String eqpname) {
        this.eqpname = eqpname;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public List<ZxBorrows> getZxBorrowsList() {
        return zxBorrowsList;
    }

    public void setZxBorrowsList(List<ZxBorrows> zxBorrowsList) {
        this.zxBorrowsList = zxBorrowsList;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getEqpstatus() {
        return eqpstatus;
    }

    public void setEqpstatus(String eqpstatus) {
        this.eqpstatus = eqpstatus;
    }

    public List<Long> getEqpIds() {
        return eqpIds;
    }

    public void setEqpIds(List<Long> eqpIds) {
        this.eqpIds = eqpIds;
    }
}
