package com.itycu.server.app.vo.zonghang;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 总行数据
 * @author lch
 * @create 2020-03-23 14:22
 */
@Data
@Slf4j
public class ZongHangMonthNumber implements Serializable {

    /** 资产总值 */
    private BigDecimal zcValue = new BigDecimal("0");

    /** 资产总值 */
    private Integer zcCount = 0;

    /** 采购总数 */
    private Integer caigouCount = 0;

    /** 调拨总数 */
    private Integer diaoboCount = 0;

    /** 盘点总数 */
    private Integer pandianCount = 0;

    /** 报修总数 */
    private Integer baoxiuCount = 0;

    /** 巡检总数 */
    private Integer xunjianCount = 0;

    /** 处置总数 */
    private Integer chuzhiCount = 0;

    /** 支行列表 */
    private List<ZhiHangNumber> assetsCountList;

}
