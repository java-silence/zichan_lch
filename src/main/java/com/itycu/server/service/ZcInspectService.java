package com.itycu.server.service;

import com.itycu.server.dto.ZcInspectDto;
import com.itycu.server.page.table.PageTableRequest;

import javax.servlet.http.HttpServletResponse;

public interface ZcInspectService {
    ZcInspectDto save(ZcInspectDto zcInspect);
    ZcInspectDto update(ZcInspectDto zcInspect);
    void delete(Long id);
    ZcInspectDto zcInspectTodo(ZcInspectDto zcInspect);
    void export(PageTableRequest request, HttpServletResponse response);
}
