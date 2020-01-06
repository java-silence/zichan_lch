package com.itycu.server.service;

import com.itycu.server.dto.RepairVO;
import com.itycu.server.model.Repair;

/**
 * Created by fanlinglong on 2018/8/11.
 */
public interface RepairService {
//    String SendTodo(Long id);
    Repair save(RepairVO repairVO);
    Repair update(RepairVO repairVO);
}
