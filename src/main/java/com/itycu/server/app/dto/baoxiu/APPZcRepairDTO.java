package com.itycu.server.app.dto.baoxiu;

import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.ZcRepairItem;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * APP资产报修
 * @author lch
 * @create 2020-02-22 18:35
 */
@Data
public class APPZcRepairDTO implements Serializable {

    /** 维修ID. */
    private Long zcRepairId;

    /** 待办ID. */
    private Long flowTodoId;

    /** 审核内容. */
    private String neirong;

    /** 报修子项. */
    private List<ZcRepairItem> zcRepairItemList;

}
