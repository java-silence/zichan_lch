package com.itycu.server.service;

import com.itycu.server.dto.ZcRepairDto;
import com.itycu.server.model.ZcRepair;

public interface ZcRepairService {
    ZcRepairDto save(ZcRepairDto zcRepair);
    ZcRepairDto update(ZcRepairDto zcRepair);
    void delete(Long id);
    ZcRepairDto check(ZcRepairDto zcRepairDto);
}
