package com.itycu.server.dto;

import com.itycu.server.model.FlowTodoItem;
import java.io.Serializable;
import java.util.List;

/**
 * 资产报废审核
 * @author lch
 * @create 2019-12-02 11:16
 */
public class ZcBfCheckDto implements Serializable {

    /** 待办ID. */
    private Long id;

    private Integer type;

    private String neirong;

    private Long bfzcid;

    private Long itemStatus;

    /** 是否再次提交. */
    private Long againSubmit;

    List<FlowTodoItem> flowTodoItems;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<FlowTodoItem> getFlowTodoItems() {
        return flowTodoItems;
    }

    public void setFlowTodoItems(List<FlowTodoItem> flowTodoItems) {
        this.flowTodoItems = flowTodoItems;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
