package com.itycu.server.dto;

import com.itycu.server.model.Stockout;
import com.itycu.server.model.Stockouts;

import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/8/8 0008.
 */
public class StockoutVO extends Stockout{
    private List<Stockouts> stockoutsList;

    private String deptname;    //部门名称
    private String ctype1;    //入库类型名称
    private String whname;    //入库名称
    private String cusname;
    private String creator;
    private String auditor;

    private Long cid;   //客户分类
    private Long xianluid;  //线路id
    private String xianlu;  //线路
    private String cidname;  //分类名称

    private String smxsddtype;  //订单类型

    private String cusphone;

    private Date zcddate;
    private String zcclbm;
    private String isaudit;
    private String stepname;

    public String getZcclbm() {
        return zcclbm;
    }

    public void setZcclbm(String zcclbm) {
        this.zcclbm = zcclbm;
    }

    public String getDeptname() {return deptname;}

    public void setDeptname(String deptname) {this.deptname = deptname;}

    public String getCtype1() {return ctype1;}

    public void setCtype1(String ctype1) {this.ctype1 = ctype1;}

    public String getWhname() {return whname;}

    public void setWhname(String whname) {this.whname = whname;}

    public List<Stockouts> getStockoutsList() {
        return stockoutsList;
    }

    public void setStockoutsList(List<Stockouts> stockoutsList) {
        this.stockoutsList = stockoutsList;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getXianluid() {
        return xianluid;
    }

    public void setXianluid(Long xianluid) {
        this.xianluid = xianluid;
    }

    public String getXianlu() {
        return xianlu;
    }

    public void setXianlu(String xianlu) {
        this.xianlu = xianlu;
    }

    public String getSmxsddtype() {
        return smxsddtype;
    }

    public void setSmxsddtype(String smxsddtype) {
        this.smxsddtype = smxsddtype;
    }

    public String getCidname() {
        return cidname;
    }

    public void setCidname(String cidname) {
        this.cidname = cidname;
    }

    public String getCusphone() {
        return cusphone;
    }

    public void setCusphone(String cusphone) {
        this.cusphone = cusphone;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getZcddate() {
        return zcddate;
    }

    public void setZcddate(Date zcddate) {
        this.zcddate = zcddate;
    }

    public String getIsaudit() {
        if(isaudit ==null) isaudit="0";
        return isaudit;
    }

    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit;
    }

    public String getStepname() {
        if(getStepid() == null) return "普通";
        if(getStepid().equals(2L)){
            return "普通";
        }else if(getStepid().equals(3L)){
            return "申请";
        }else if(getStepid().equals(4L)){
            return "已派";
        }
        return "普通";
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }
}
