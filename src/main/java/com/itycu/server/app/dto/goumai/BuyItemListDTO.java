package com.itycu.server.app.dto.goumai;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyItemListDTO {

    private int id;
    /** 管理部门ID. */
    private long glDeptId;
    /** 资产名称. */
    private String name;
    private String model;
    private String unit;
    private String brand;
    private BigDecimal price;
    private String supplierName;
    /** 使用用途. */
    private String useDes;
    /** 资产数量. */
    private Integer num;
    private String zc_bz;
    private String glDeptName;
}
