package com.itycu.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CustomerService {
    Map cusImport(MultipartFile file);
}
