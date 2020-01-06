package com.itycu.server.dto;

import com.itycu.server.model.ZcChangeRecord;

public class ZcChangeRecordDto extends ZcChangeRecord {
    private String zcCategoryName;
    private String glDeptName;
    private String syDeptName;
    private String updateor;
    private String useStatusName;
    public String getZcCategoryName() {
        return zcCategoryName;
    }

    public void setZcCategoryName(String zcCategoryName) {
        this.zcCategoryName = zcCategoryName;
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

    public String getUpdateor() {
        return updateor;
    }

    public void setUpdateor(String updateor) {
        this.updateor = updateor;
    }

    public String getUseStatusName() {
        return useStatusName;
    }

    public void setUseStatusName(String useStatusName) {
        this.useStatusName = useStatusName;
    }
}
