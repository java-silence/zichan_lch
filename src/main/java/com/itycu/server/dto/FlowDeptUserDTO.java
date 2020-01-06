package com.itycu.server.dto;

import java.io.Serializable;

/**
 * 节点部门用户接收对象
 * @author lch
 * @create 2019-11-27 14:32
 */
public class FlowDeptUserDTO implements Serializable {

    private Long id;

    private Long userid;

    private Long deptid;

    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
