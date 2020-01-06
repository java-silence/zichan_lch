package com.itycu.server.dto;

import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.ZcRepair;
import com.itycu.server.model.ZcRepairItem;

import java.util.List;

public class ZcRepairDto extends ZcRepair {
    private List<ZcRepairItem> zcRepairItemList;
    private List<FlowTodoItem> flowTodoItems;
    private String nickname;
    private String deptname;
    private String confirmNickname;
    private String confirmDeptname;
    private String neirong;

    private Long bfzcid;

    private Long itemStatus;

    /** 是否再次提交. */
    private Long againSubmit;

    public List<ZcRepairItem> getZcRepairItemList() {
        return zcRepairItemList;
    }

    public void setZcRepairItemList(List<ZcRepairItem> zcRepairItemList) {
        this.zcRepairItemList = zcRepairItemList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public List<FlowTodoItem> getFlowTodoItems() {
        return flowTodoItems;
    }

    public void setFlowTodoItems(List<FlowTodoItem> flowTodoItems) {
        this.flowTodoItems = flowTodoItems;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public Long getBfzcid() {
        return bfzcid;
    }

    public void setBfzcid(Long bfzcid) {
        this.bfzcid = bfzcid;
    }

    public Long getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Long itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getAgainSubmit() {
        return againSubmit;
    }

    public void setAgainSubmit(Long againSubmit) {
        this.againSubmit = againSubmit;
    }

    public String getConfirmNickname() {
        return confirmNickname;
    }

    public void setConfirmNickname(String confirmNickname) {
        this.confirmNickname = confirmNickname;
    }

    public String getConfirmDeptname() {
        return confirmDeptname;
    }

    public void setConfirmDeptname(String confirmDeptname) {
        this.confirmDeptname = confirmDeptname;
    }
}
