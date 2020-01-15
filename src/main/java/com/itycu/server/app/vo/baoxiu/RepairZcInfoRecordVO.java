package com.itycu.server.app.vo.baoxiu;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RepairZcInfoRecordVO {

    private int id;


    private String code;


    private String nickname;

    private String deptname;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
