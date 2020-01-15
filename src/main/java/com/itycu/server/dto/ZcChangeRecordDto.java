package com.itycu.server.dto;

import com.itycu.server.model.ZcChangeRecord;

/**
 * 资产变动记录
 */
public class ZcChangeRecordDto extends ZcChangeRecord {

    private String zcCategoryName;
    private String glDeptName;
    private String syDeptName;
    private String updateor;
    private String useStatusName;

    private String cateName1;
    private String cateName2;
    private String cateName3;
    private String cateName4;


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

    public String getCateName1() {
        return cateName1;
    }

    public void setCateName1(String cateName1) {
        this.cateName1 = cateName1;
    }

    public String getCateName2() {
        return cateName2;
    }

    public void setCateName2(String cateName2) {
        this.cateName2 = cateName2;
    }

    public String getCateName3() {
        return cateName3;
    }

    public void setCateName3(String cateName3) {
        this.cateName3 = cateName3;
    }

    public String getCateName4() {
        return cateName4;
    }

    public void setCateName4(String cateName4) {
        this.cateName4 = cateName4;
    }
}
