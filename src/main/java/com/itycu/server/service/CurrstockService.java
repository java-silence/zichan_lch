package com.itycu.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CurrstockService {
    Map currImport(MultipartFile file);

    void currImportInv(Long whid, Long invcid);
}
