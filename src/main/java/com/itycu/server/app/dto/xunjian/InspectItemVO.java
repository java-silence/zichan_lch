package com.itycu.server.app.dto.xunjian;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InspectItemVO {



    private int item_id;


    private int xunjian_id;

    /**
     * 外观
     */
    private String apperance;

    /**
     * 功能
     */
    private String function;

    /**
     * 结果
     */
    private String result;

    /**
     * 巡检意见
     */
    private String opinion;


    /**
     * 图片底子
     */
    private String img;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date update_time;


    private int gl_dept_id;


    private int sy_dept_id;



}
