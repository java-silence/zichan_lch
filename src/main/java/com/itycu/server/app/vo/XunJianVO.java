package com.itycu.server.app.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class XunJianVO {

    private int id;

    private String epcid;


    private long sy_dept_id;

    private String syDeptName;

    private long gl_dept_id;


    private String glDeptName;


    private String store_address;


    private String zc_name;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;



    private int status;

    /**
     * 巡检周期
     */
    private String xunjianTime;

}
