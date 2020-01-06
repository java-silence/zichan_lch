package com.itycu.server.dto;

import com.itycu.server.model.FlowTodoItem;
import java.io.Serializable;
import java.util.List;

/**
 * @author lch
 * @create 2019-12-07 10:43
 */
public class ZcDeployCheckDto implements Serializable {

    private Long id;

    private Integer type;

    private String neirong;

    private Long deployId;

    private Long todoId;

    private Long itemStatus;

    /** 是否再次提交. */
    private Long againSubmit;

    List<FlowTodoItem> flowTodoItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<FlowTodoItem> getFlowTodoItems() {
        return flowTodoItems;
    }

    public void setFlowTodoItems(List<FlowTodoItem> flowTodoItems) {
        this.flowTodoItems = flowTodoItems;
    }

    public Long getDeployId() {
        return deployId;
    }

    public void setDeployId(Long deployId) {
        this.deployId = deployId;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }
}
