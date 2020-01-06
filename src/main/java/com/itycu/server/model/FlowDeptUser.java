package com.itycu.server.model;

/**
 * 流程节点部门用户
 */
public class FlowDeptUser extends BaseEntity<Long> {

	private Integer flowId;
	private Integer flowstepId;
	private Integer deptId;
	private Integer userId;
	private Integer del;
	private String bz;

	public Integer getFlowId() {
		return flowId;
	}
	public void  setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public Integer getFlowstepId() {
		return flowstepId;
	}
	public void  setFlowstepId(Integer flowstepId) {
		this.flowstepId = flowstepId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void  setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void  setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}

}
