package com.itycu.server.app.dto.pandian;


import lombok.Data;

/**
 * 盘点记录中的表 盘盈和盘亏资产列表
 */
@Data
public class CheckItemReportDTO {


    private int offset;

    private int limit;


    private int zcCheckId;

    /**
     * 盘盈盘亏的资产 状态
     */
    private int type=0;

}
