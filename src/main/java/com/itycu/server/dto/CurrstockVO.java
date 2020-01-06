package com.itycu.server.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.itycu.server.model.Currstock;

/**
 * Created by fanlinglong on 2018/9/9.
 */
@ExcelTarget("currstock")
public class CurrstockVO extends Currstock {
    private String whname;
    private String deptname;
    @Excel(name = "备件名称")
    private String invname;
    @Excel(name = "品牌")
    private String invabbname;
    @Excel(name = "型号")
    private String invstd;
    private String unit1;
    private String statusname;

    private String whtype;

    private String cusname;

    public String getWhname() {
        return whname;
    }

    public void setWhname(String whname) {
        this.whname = whname;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getInvabbname() {
        return invabbname;
    }

    public void setInvabbname(String invabbname) {
        this.invabbname = invabbname;
    }

    public String getInvstd() {
        return invstd;
    }

    public void setInvstd(String invstd) {
        this.invstd = invstd;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getWhtype() {
        return whtype;
    }

    public void setWhtype(String whtype) {
        this.whtype = whtype;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }
}
