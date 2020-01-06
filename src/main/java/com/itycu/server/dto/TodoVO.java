package com.itycu.server.dto;

import com.itycu.server.model.Todo;

/**
 * Created by Hezhilin on 2018/3/11 0011.
 */
public class TodoVO extends Todo {
    private String sendname;    //发送人姓名
    private String auditname;   //审批人姓名

    private String senddeptid;  //发送人部门ID
    private String senddeptname;    //发送人部门名称

    private String tofinish;  //是否是最后一步

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname;
    }

    public String getSenddeptid() {
        return senddeptid;
    }

    public void setSenddeptid(String senddeptid) {
        this.senddeptid = senddeptid;
    }

    public String getSenddeptname() {
        return senddeptname;
    }

    public void setSenddeptname(String senddeptname) {
        this.senddeptname = senddeptname;
    }

    public String getTofinish() {
        return tofinish;
    }

    public void setTofinish(String tofinish) {
        this.tofinish = tofinish;
    }
}
