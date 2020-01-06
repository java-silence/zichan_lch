package com.itycu.server.service;

import java.io.IOException;

import com.itycu.server.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
