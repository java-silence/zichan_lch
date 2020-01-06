package com.itycu.server.model;

/**
 * 财务订单
 */
public class ZcBfCwitem extends BaseEntity<Long> {

	private String codeNum;

	private String todoIds;

	private String creator;

	private Long todoId;

	private Long bfId;

	public String getCodeNum() {
		return codeNum;
	}

	public void  setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

    public String getTodoIds() {
        return todoIds;
    }

    public void setTodoIds(String todoIds) {
        this.todoIds = todoIds;
    }

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public Long getBfId() {
        return bfId;
    }

    public void setBfId(Long bfId) {
        this.bfId = bfId;
    }
}
