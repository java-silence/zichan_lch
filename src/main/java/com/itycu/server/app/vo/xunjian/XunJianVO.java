package com.itycu.server.app.vo.xunjian;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class XunJianVO {

    private int id;

    /**
     * 资产追溯码
     */
    private String epcid;

    /**
     * 使用部门id
     */
    private long syDeptId;

    /**
     * 使用部门名称
     */
    private String syDeptName;


    /**
     * 管理部门id
     */
    private long glDeptId;


    /**
     * 管理部门名称
     */
    private String glDeptName;


    /**
     * 存储地址
     */
    private String storeAddress;


    /**
     * 资产名称
     */
    private String zcName;


    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;


    /**
     * 巡检状态
     */
    private int status;

    /**
     * 巡检周期
     */
    private String inspectTime;


    /**
     * 巡检结果
     */
    private String result;


    /**
     * 资产编码
     */
    private String zcCodenum;


    /**
     * 巡检关系id
     */
    private int zcRealId;

}
