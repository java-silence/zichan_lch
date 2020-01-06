package com.itycu.server.dto;

import com.itycu.server.model.Dept;
import com.itycu.server.model.Repair;
import com.itycu.server.model.Repairs;
import com.itycu.server.model.SysUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanlinglong on 2018/8/8 0008.
 */
public class RepairVO extends Repair{
    private List<Repairs> repairsList;

    private List<String> fileIds;
    private Integer send;   //保存同时是否自动发送提交 0不提交   1提交
    private Dept dept;
    private SysUser sysUser;

    String cname;       //设备名称
    String createname;  //申请人
    String deptname;    //部门名称
    String biztype1;    //维修类型名称
    String stepname;    //审批状态
    String biaoti;      //todo标题

    String todoid;  //待办ID
    String todoauditby; //待办审批人
    String todostatus;  //待办审批状态
    private String xtname;

    private String statusname;

    private String monthgzs; //月故障小时

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {this.send = send;}

    public List<Repairs> getRepairsList() {
        return repairsList;
    }

    public void setRepairsList(List<Repairs> repairsList) {
        this.repairsList = repairsList;
    }

    public String getCname() {return cname;}

    public void setCname(String cname) {this.cname = cname;}

    public String getDeptname() {return deptname;}

    public void setDeptname(String deptname) {this.deptname = deptname;}

    public String getBiztype1() {return biztype1;}

    public void setBiztype1(String biztype1) {this.biztype1 = biztype1;}

    public String getTodoid() {
        return todoid;
    }

    public void setTodoid(String todoid) {
        this.todoid = todoid;
    }

    public String getTodoauditby() {
        return todoauditby;
    }

    public void setTodoauditby(String todoauditby) {
        this.todoauditby = todoauditby;
    }

    public String getTodostatus() {
        return todostatus;
    }

    public void setTodostatus(String todostatus) {
        this.todostatus = todostatus;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getXtname() {
        return xtname;
    }

    public void setXtname(String xtname) {
        this.xtname = xtname;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getMonthgzs() {
        return monthgzs;
    }

    public void setMonthgzs(String monthgzs) {
        this.monthgzs = monthgzs;
    }
}
