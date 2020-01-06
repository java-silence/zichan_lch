package com.itycu.server.dto;

import com.itycu.server.model.ZcCheck;

public class ZcCheckDto extends ZcCheck {
    //检查部门
    private String checkDeptName;

    public String getCheckDeptName() {
        return checkDeptName;
    }

    public void setCheckDeptName(String checkDeptName) {
        this.checkDeptName = checkDeptName;
    }
}
