package com.itycu.server.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.itycu.server.model.ZcInfo;

public class ZcInfoDto extends ZcInfo {
    @Excel(name = "" , fixedIndex = 4)
    private String zcCategoryName;
    @Excel(name = "" , fixedIndex = 5)
    private String glDeptName;
    @Excel(name = "" , fixedIndex = 6)
    private String syDeptName;
    private String updateor;
    private String useStatusName;
    private Integer quantity;           //数量，用于导入复制多少数量资产
    private Long zcCategoryPid; //资产分类上级Id
    private String zcCategoryYiJi; //资产分类一级分类
    private String zcCategoryErJi; //资产分类二级分类
    private String cateName1;
    private String cateName2;
    private String cateName3;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getZcCategoryPid() {
        return zcCategoryPid;
    }

    public void setZcCategoryPid(Long zcCategoryPid) {
        this.zcCategoryPid = zcCategoryPid;
    }

    public String getZcCategoryYiJi() {
        return zcCategoryYiJi;
    }

    public void setZcCategoryYiJi(String zcCategoryYiJi) {
        this.zcCategoryYiJi = zcCategoryYiJi;
    }

    public String getZcCategoryErJi() {
        return zcCategoryErJi;
    }

    public void setZcCategoryErJi(String zcCategoryErJi) {
        this.zcCategoryErJi = zcCategoryErJi;
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
}
