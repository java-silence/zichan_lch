package com.itycu.server.dto;

import com.itycu.server.model.ZcBf;
import com.itycu.server.model.ZcBfItem;

import java.util.List;

public class ZcBfDto extends ZcBf {
    private List<ZcBfItem> zcBfItemList;

    public List<ZcBfItem> getZcBfItemList() {
        return zcBfItemList;
    }

    public void setZcBfItemList(List<ZcBfItem> zcBfItemList) {
        this.zcBfItemList = zcBfItemList;
    }
}
