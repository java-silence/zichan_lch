package com.itycu.server.dto;

import com.itycu.server.model.FlowTodoItem;

import java.io.Serializable;
import java.util.List;

/**
 * 资产购买审核
 * @author lch
 * @create 2019-12-02 11:16
 */
public class ZcBuyCheckDto implements Serializable {

    private Integer type;

    private String neirong;

    private Long zcBuyId;

    private Long todoId;

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

    public Long getZcBuyId() {
        return zcBuyId;
    }

    public void setZcBuyId(Long zcBuyId) {
        this.zcBuyId = zcBuyId;
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

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }
}
