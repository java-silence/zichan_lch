package com.itycu.server.app.vo.pandian;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ZcCheckDetailReportVO {


    private int id;

    private String checkNum;

    private String syDeptName;

    private String checkUserName;

    private int result;

    private String strResult;

    private int total;

    private int normal;

    private int errorNum;

    private int status;

    private String statusFlag;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date checkTime;


    private int checkDeptId;

    private int reCheck;

    private String reCheckFlag;


    private int profit;


}
