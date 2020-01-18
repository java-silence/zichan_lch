package com.itycu.server.app.dto.goumai;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyItemListDTO {


    private int id;

    private long glDeptId;

    private String name;

    private String model;
    private String unit;

    private String brand;

    private BigDecimal price;

    private String supplierName;

    private String useDes;

    private int num;

    private String zc_bz;

    private String glDeptName;
}
