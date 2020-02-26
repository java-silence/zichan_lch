package com.itycu.server.service;

import com.itycu.server.app.dto.baoxiu.APPZcRepairDTO;
import com.itycu.server.dto.ZcRepairDto;

public interface ZcRepairService {

    ZcRepairDto save(ZcRepairDto zcRepair);

    ZcRepairDto update(ZcRepairDto zcRepair);

    void delete(Long id);

    ZcRepairDto check(ZcRepairDto zcRepairDto);

    /**
     * APP 维修审核
     * @param zcRepairDTO
     * @return
     */
    String appCheck(APPZcRepairDTO zcRepairDTO);
}
