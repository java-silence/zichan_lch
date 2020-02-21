package com.itycu.server.app.dto.goumai;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyItemListDTO {

    /** 主键ID. */
    private int id;
    /** 管理部门ID. */
    private long glDeptId;
    /** 资产名称. */
    private String name;
    /** 模型. */
    private String model;
    /** 单位. */
    private String unit;
    /** 品牌. */
    private String brand;
    /** 单价. */
    private BigDecimal price;
    /** 供应商名称. */
    private String supplierName;
    /** 使用用途. */
    private String useDes;
    /** 资产数量. */
    private Integer num;
    /** 资产笨猪. */
    private String zc_bz;
    /** 管理部门名称. */
    private String glDeptName;
}
