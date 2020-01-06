package com.itycu.server.service;

import com.itycu.server.model.ZcInfo;
import com.itycu.server.page.table.PageTableRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ZcInfoService {
    ZcInfo save(ZcInfo zcInfo);
    ZcInfo update(ZcInfo zcInfo);
    void delete(Long id);
    void export(PageTableRequest request, HttpServletResponse response);
    Map Import(MultipartFile file) throws IOException;
    Map gudingImport(MultipartFile file) throws IOException;
    Map dizhiImport(MultipartFile file) throws IOException;
}
