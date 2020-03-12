package com.itycu.server.app.dto.pandian;


import lombok.Data;

@Data
public class ZxCheckListItemDTO {


    private long id;

    private int offset=1;

    private int limit=10;

    /** 盘点状态 未盘点:0 已盘点:1 */
    private Integer type;

}
