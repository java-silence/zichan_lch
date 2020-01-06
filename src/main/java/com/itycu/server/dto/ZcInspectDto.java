package com.itycu.server.dto;

import com.itycu.server.model.ZcInspect;
import com.itycu.server.model.ZcInspectRecord;

import java.util.List;

public class ZcInspectDto extends ZcInspect {
    private List<ZcInspectRecord> zcInspectRecordList;


    public List<ZcInspectRecord> getZcInspectRecordList() {
        return zcInspectRecordList;
    }

    public void setZcInspectRecordList(List<ZcInspectRecord> zcInspectRecordList) {
        this.zcInspectRecordList = zcInspectRecordList;
    }
}
