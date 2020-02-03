package com.itycu.server.app.dto.goumai;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InsertBuyDataDTO {
    private int type;
    private String companyName;
    private String fileUrl;
    private String fileName;
    private long id;
    private String name;
    private int num;
    private String useDes;
    private int glDeptId;
    private String model;
    private String unit;
    private String brand;
    private BigDecimal price;
    private String supplierName;
    private List<BuyItemListDTO> zcBuyItemList;
}
