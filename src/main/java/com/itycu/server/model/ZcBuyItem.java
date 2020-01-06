package com.itycu.server.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZcBuyItem extends BaseEntity<Long> {

	private Long zcBuyId;
	private Long glDeptId;
	private String name;
	private String model;
	private String unit;
	private String brand;
	private BigDecimal price;
	private String supplierName;
	private String useDes;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date arriveTime;
	private String zcBz;
	private Integer shbStatus;
	private Integer cwbStatus;
	private Integer status;
	private String bz;
	private Integer del;
	private Integer num;
	private String fileName;
	private String fileUrl;
	private BigDecimal totalPrice;
	private Long applyUserId;
	private String creator;

	public Long getZcBuyId() {
		return zcBuyId;
	}
	public void  setZcBuyId(Long zcBuyId) {
		this.zcBuyId = zcBuyId;
	}
	public Long getGlDeptId() {
		return glDeptId;
	}
	public void  setGlDeptId(Long glDeptId) {
		this.glDeptId = glDeptId;
	}
	public String getName() {
		return name;
	}
	public void  setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void  setModel(String model) {
		this.model = model;
	}
	public String getUnit() {
		return unit;
	}
	public void  setUnit(String unit) {
		this.unit = unit;
	}
	public String getBrand() {
		return brand;
	}
	public void  setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void  setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void  setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getUseDes() {
		return useDes;
	}
	public void  setUseDes(String useDes) {
		this.useDes = useDes;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void  setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getZcBz() {
		return zcBz;
	}
	public void  setZcBz(String zcBz) {
		this.zcBz = zcBz;
	}
	public Integer getShbStatus() {
		return shbStatus;
	}
	public void  setShbStatus(Integer shbStatus) {
		this.shbStatus = shbStatus;
	}
	public Integer getCwbStatus() {
		return cwbStatus;
	}
	public void  setCwbStatus(Integer cwbStatus) {
		this.cwbStatus = cwbStatus;
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
	public Integer getDel() {
		return del;
	}
	public void  setDel(Integer del) {
		this.del = del;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTextTotalPrice(){
	    return this.price.multiply(new BigDecimal(this.num));
    }

}
