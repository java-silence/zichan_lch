package com.itycu.server.app.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppIndexZcValueAndNumber {


    /**
     * 资产总值
     */
    private BigDecimal zcValue;

    /**
     * 资产数量
     */
    private int zcCount = 0;


    /**
     * 采购数量
     */
    private int caigouCount = 0;

    /**
     * 调拨数据
     */
    private int diaoboCount = 0;

    /**
     * 盘点数量
     */
    private int pandianCount = 0;


    /**
     * 保修数量
     */
    private int baoxiuCount = 0;

    /**
     * 巡检数量
     */
    private int xunjianCount = 0;

    /**
     * 处置数量
     */
    private int chuzhiCount = 0;


    /**
     * 保卫部资产数量
     */
    private int bwbZcCount = 0;

    /**
     * 科技部资产数量
     */
    private int kjbZcCount = 0;

    /**
     * 综合办资产数量
     */
    private int zhbZcCount = 0;


    /**
     * 运营部资产数量
     */
    private int yybZcCount = 0;

}
