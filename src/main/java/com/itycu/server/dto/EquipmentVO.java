package com.itycu.server.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.itycu.server.model.Equipment;


import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/8/11.
 */
@ExcelTarget("equipment")
public class EquipmentVO extends Equipment {
    @Excel(name = "设备分类")
    String xtfl;        //系统分类
    @Excel(name = "设备类别")
    String sblb;        //设备类别
    @Excel(name = "所属站")
    String deptname;    //部门名称
    @Excel(name = "配属")
    String xjqyname;    //巡检区域
    String whqyname;    //维护区域
    @Excel(name = "库房")
    String whname;      //仓库
    String statusname;  //设备状态
    String creater;     //创建人
    Date jyTime;  //借用时间
    String jyname;     //借用人

    public String getXtfl() {
        return xtfl;
    }

    public void setXtfl(String xtfl) {
        this.xtfl = xtfl;
    }

    public String getSblb() {
        return sblb;
    }

    public void setSblb(String sblb) {
        this.sblb = sblb;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getXjqyname() {
        return xjqyname;
    }

    public void setXjqyname(String xjqyname) {
        this.xjqyname = xjqyname;
    }

    public String getWhqyname() {
        return whqyname;
    }

    public void setWhqyname(String whqyname) {
        this.whqyname = whqyname;
    }

    public String getWhname() {
        return whname;
    }

    public void setWhname(String whname) {
        this.whname = whname;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getJyTime() {
        return jyTime;
    }

    public void setJyTime(Date jyTime) {
        this.jyTime = jyTime;
    }

    public String getJyname() {
        return jyname;
    }

    public void setJyname(String jyname) {
        this.jyname = jyname;
    }
}
