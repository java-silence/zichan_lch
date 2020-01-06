package com.itycu.server.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.itycu.server.model.Stockouts;
import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/8/11.
 */
public class StockoutsVO extends Stockouts {
    String invname;        //系统分类
    private String whname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ddate;
    private Long cusid;
    private String cusname;
    private String mxwhname;
    private String outctype; //主表的类型
    private String clbm; //车辆编码
    private String fkfs;

    private String dwmark;

    private String sjname;

    private String zcmemo;
    private String creater;

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getWhname() {
        return whname;
    }

    public void setWhname(String whname) {
        this.whname = whname;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getMxwhname() {
        return mxwhname;
    }

    public void setMxwhname(String mxwhname) {
        this.mxwhname = mxwhname;
    }

    public String getOutctype() {
        return outctype;
    }

    public void setOutctype(String outctype) {
        this.outctype = outctype;
    }

    public String getClbm() {
        return clbm;
    }

    public void setClbm(String clbm) {
        this.clbm = clbm;
    }

    public String getFkfs() {
        return fkfs;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }

    public String getDwmark() {
        return dwmark;
    }

    public void setDwmark(String dwmark) {
        this.dwmark = dwmark;
    }

    public String getSjname() {
        return sjname;
    }

    public void setSjname(String sjname) {
        this.sjname = sjname;
    }

    public String getZcmemo() {
        return zcmemo;
    }

    public void setZcmemo(String zcmemo) {
        this.zcmemo = zcmemo;
    }

    public Long getCusid() {
        return cusid;
    }

    public void setCusid(Long cusid) {
        this.cusid = cusid;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
