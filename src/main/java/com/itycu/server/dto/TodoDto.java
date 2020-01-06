package com.itycu.server.dto;

import com.itycu.server.model.SysUser;
import com.itycu.server.model.Todo;

import java.util.List;

/**
 * Created by Hezhilin on 2018/3/11 0011.
 */
public class TodoDto extends Todo {
    private List<String> fileIds;

    private Integer send;   //保存同时是否自动发送提交 0不提交   1提交

    private String back;    //流程驳回

    private Long userid;    //指定的Userid 如：维修派单指定维修员等

    private String canshu1;
    private String canshu2;
    private String canshu3;

    private Long jumpto;    //跳转到第几步

    private SysUser sendUser;

    private SysUser auditUser;

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

    public SysUser getSendUser() {
        return sendUser;
    }

    public void setSendUser(SysUser sendUser) {
        this.sendUser = sendUser;
    }

    public SysUser getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(SysUser auditUser) {
        this.auditUser = auditUser;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getJumpto() {
        return jumpto;
    }

    public void setJumpto(Long jumpto) {
        this.jumpto = jumpto;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getCanshu1() {
        return canshu1;
    }

    public void setCanshu1(String canshu1) {
        this.canshu1 = canshu1;
    }

    public String getCanshu2() {
        return canshu2;
    }

    public void setCanshu2(String canshu2) {
        this.canshu2 = canshu2;
    }

    public String getCanshu3() {
        return canshu3;
    }

    public void setCanshu3(String canshu3) {
        this.canshu3 = canshu3;
    }
}
