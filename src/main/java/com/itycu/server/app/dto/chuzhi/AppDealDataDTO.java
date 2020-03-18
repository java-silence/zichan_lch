package com.itycu.server.app.dto.chuzhi;

import com.itycu.server.model.FlowTodoItem;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 处置数据接收
 * @author lch
 * @create 2020-03-17 9:24
 */
@Data
@Slf4j
public class AppDealDataDTO implements Serializable {

    /** 待办ID */
    private Long todoid;

    /** 报废主表ID */
    private Long bizid;

    private String type;

    private String neirong;

    private Long itemStatus;

    /** 是否再次提交. */
    private Long againSubmit;

    List<FlowTodoItem> flowTodoItems;

}
