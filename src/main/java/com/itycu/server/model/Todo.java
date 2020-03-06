package com.itycu.server.model;



public class Todo extends BaseEntity<Long> {

    // 类型
    private Integer type;

    private Long auditby;
    private Long sendby;
    private String biaoti;
    private String neirong;
    private String biztype;
    private Long bizid;
    private String biztable;
    private Long bizcreateby;
    private Long bizdeptid;
    private String status;
    private String memo;
    private String c01;

    private String c02;
    private String c03;
    private Long flowid;
    private Long stepid;
    private String url;

    /**
     * 审核待办集合.
     */
    private String todoIds;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getFlowid() {
        return flowid;
    }

    public void setFlowid(Long flowid) {
        this.flowid = flowid;
    }

    public Long getStepid() {
        return stepid;
    }

    public void setStepid(Long stepid) {
        this.stepid = stepid;
    }

    public Long getAuditby() {
        return auditby;
    }

    public void setAuditby(Long auditby) {
        this.auditby = auditby;
    }

    public Long getSendby() {
        return sendby;
    }

    public void setSendby(Long sendby) {
        this.sendby = sendby;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public Long getBizid() {
        return bizid;
    }

    public void setBizid(Long bizid) {
        this.bizid = bizid;
    }

    public String getBiztable() {
        return biztable;
    }

    public void setBiztable(String biztable) {
        this.biztable = biztable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getC01() {
        return c01;
    }

    public void setC01(String c01) {
        this.c01 = c01;
    }

    public String getC02() {
        return c02;
    }

    public void setC02(String c02) {
        this.c02 = c02;
    }

    public String getC03() {
        return c03;
    }

    public void setC03(String c03) {
        this.c03 = c03;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getBizcreateby() {
        return bizcreateby;
    }

    public void setBizcreateby(Long bizcreateby) {
        this.bizcreateby = bizcreateby;
    }

    public Long getBizdeptid() {
        return bizdeptid;
    }

    public void setBizdeptid(Long bizdeptid) {
        this.bizdeptid = bizdeptid;
    }

    public String getTodoIds() {
        return todoIds;
    }

    public void setTodoIds(String todoIds) {
        this.todoIds = todoIds;
    }
}
