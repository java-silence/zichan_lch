package com.itycu.server.dto;

import com.itycu.server.model.Role;
import com.itycu.server.model.SysUser;

import java.util.List;

public class SysUserDto extends SysUser {
    private String deptname;
    private String deptcode;
    private Long whid;
    private String whname;
    private List<Role> roles;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getWhid() {
        return whid;
    }

    public void setWhid(Long whid) {
        this.whid = whid;
    }

    public String getWhname() {
        return whname;
    }

    public void setWhname(String whname) {
        this.whname = whname;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
}
