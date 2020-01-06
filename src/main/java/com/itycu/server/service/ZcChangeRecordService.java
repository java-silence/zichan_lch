package com.itycu.server.service;

import com.itycu.server.model.ZcChangeRecord;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.page.table.PageTableRequest;

import javax.servlet.http.HttpServletResponse;

public interface ZcChangeRecordService {
    ZcChangeRecord save(ZcInfo zcInfo);
    void export(PageTableRequest request, HttpServletResponse response);
}
