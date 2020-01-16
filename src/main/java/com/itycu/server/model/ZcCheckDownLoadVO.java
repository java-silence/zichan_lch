package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Data
@ToString
public class ZcCheckDownLoadVO {


    /**
     * 主键
     */
    private long id;


    /**
     * '盘点部门'
     */
    private long checkDeptId;


    /**
     * '盘点人员'
     */
    private long checkUserId;

    /**
     * '盘点时间'
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date checkTime;


    /**
     * '资产总数'
     */
    private int total;


    /**
     * '盘点状态 0:待盘点 1:盘点中 2盘点完成 3再次盘点数据'
     */
    private int status;

    /**
     * '盘点结果 1 盘点正常  2盘点异常  0 待盘点'
     */
    private int result;

    /**
     * '删除状态 0:正常 1:删除'
     */
    private int del;


    /**
     * '备注'
     */
    private String bz;


    /**
     * '创建人'
     */
    private int createBy;


    /**
     * '更新人'
     */
    private int updateBy;


    /**
     * '创建时间'
     */
    private Date createTime;


    /**
     * '更新时间'
     */
    private Date updateTime;


    /**
     * '盘点次数'
     */
    private int checkTimes;


    /**
     * 再次盘点的标准 0表示没有再次盘点  1 表示再次盘点
     */
    private int reCheck;


    /**
     * '盘点编号'
     */
    private int bh;


    /**
     * 是否盘盈 0 否 1是
     */
    private int profit;



    private List<ZcCheckDownLoadItemVO> zcCheckDownLoadItemVOList;

}
