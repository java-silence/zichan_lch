package com.itycu.server.app.vo.zonghang;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 总行及各支行数据
 * @author lch
 * @create 2020-03-23 15:16
 */
@Data
@Slf4j
public class ZongHangZcInfo implements Serializable {

    /** 资产ID */
    private Long id;

    /** 净值 */
    private BigDecimal netvalue;

    /** 使用部门ID */
    private Long syDeptId;

    /** 管理部门ID */
    private Long glDeptId;

    /** 使用部门名称 */
    private String syDeptName;

    /** 管理部门名称 */
    private String glDeptName;

}
