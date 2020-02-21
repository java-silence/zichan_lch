package com.itycu.server.app.dto.goumai;

import com.itycu.server.model.ZcBuyItem;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class InsertBuyDataDTO {
    /** 是否启动流程,前端不用传递此参数. */
    private int type;
    /** 部门名称. */
    private String companyName;
    /** 附件相对地址. */
    private String fileUrl;
    /** 附件名称. */
    private String fileName;
    /** 主键ID. */
    private long id;
    /** 资产名称. */
    private String name;
    /** 资产购买数量. */
    private int num;
    /** 购买使用用途. */
    private String useDes;
    /** 管理部门ID. */
    private int glDeptId;
    /** 规格. */
    private String model;
    /** 型号. */
    private String unit;
    /** 品牌. */
    private String brand;
    /** 单价. */
    private BigDecimal price;
    /** 供应商名称. */
    private String supplierName;
    /** 购买子项列表. */
    private List<ZcBuyItem> zcBuyItemList;
}
