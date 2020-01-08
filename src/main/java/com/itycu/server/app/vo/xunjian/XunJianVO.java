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
    private long sy_dept_id;

    /**
     * 使用部门名称
     */
    private String syDeptName;


    /**
     * 管理部门id
     */
    private long gl_dept_id;


    /**
     * 管理部门名称
     */
    private String glDeptName;


    /**
     * 存储地址
     */
    private String store_address;


    /**
     * 资产名称
     */
    private String zc_name;


    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date create_time;


    /**
     * 巡检状态
     */
    private int status;

    /**
     * 巡检周期
     */
    private String inspect_time;


    /**
     * 标记是否已经巡检
     */
    private String c03;

}
