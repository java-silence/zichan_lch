package com.itycu.server.model;

import java.math.BigDecimal;

public class FlowTodoItem extends BaseEntity<Long> {

    /** 主键ID. */
    private Long id;

	private Long flowTodoId;
	private Long flowItemId;
	private Integer status;
	private String bz;
	private String content;

	private Long auditby;
	private Long sendby;

	private Integer shbStatus;
	private Integer cwbStatus;

	// -------------购买item信息
    private String name;
    private String model;
    private String unit;
    private String brand;
    private BigDecimal price;
    private String supplierName;
    private String useDes;
    private String zcBz;
    private Integer num;
    private BigDecimal totalPrice;

    // 鉴定意见
    private String identifyContent;
	// -------------购买item信息

	/**
	 * 制单人（多表关联查询字段）
	 */
	private String creator;
	/**
	 * 部门名称（多表关联查询字段）
	 */
//	private String deptname;

	public Long getFlowTodoId() {
		return flowTodoId;
	}
	public void  setFlowTodoId(Long flowTodoId) {
		this.flowTodoId = flowTodoId;
	}
	public Long getFlowItemId() {
		return flowItemId;
	}
	public void  setFlowItemId(Long flowItemId) {
		this.flowItemId = flowItemId;
	}
	public Integer getStatus() {
		return status;
	}
	public void  setStatus(Integer status) {
		this.status = status;
	}
	public String getBz() {
		return bz;
	}
	public void  setBz(String bz) {
		this.bz = bz;
	}
	public String getContent() {
		return content;
	}
	public void  setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

    public Integer getShbStatus() {
        return shbStatus;
    }

    public void setShbStatus(Integer shbStatus) {
        this.shbStatus = shbStatus;
    }

    public Integer getCwbStatus() {
        return cwbStatus;
    }

    public void setCwbStatus(Integer cwbStatus) {
        this.cwbStatus = cwbStatus;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUseDes() {
        return useDes;
    }

    public void setUseDes(String useDes) {
        this.useDes = useDes;
    }

    public String getZcBz() {
        return zcBz;
    }

    public void setZcBz(String zcBz) {
        this.zcBz = zcBz;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifyContent() {
        return identifyContent;
    }

    public void setIdentifyContent(String identifyContent) {
        this.identifyContent = identifyContent;
    }
}
