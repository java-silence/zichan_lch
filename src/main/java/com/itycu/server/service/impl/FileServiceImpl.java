package com.itycu.server.service.impl;

import java.io.IOException;

import com.itycu.server.service.FileService;
import com.itycu.server.utils.FileUtil;
import com.itycu.server.dao.FileInfoDao;
import com.itycu.server.model.FileInfo;
import com.itycu.server.utils.ZiChanCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Value("${files.path}")
	private String filesPath;
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public FileInfo save(MultipartFile file) throws IOException {
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}

		// String md5 = FileUtil.fileMd5(file.getInputStream());
		String md5 = ZiChanCodeUtil.RandomCode();
		FileInfo fileInfo = fileInfoDao.getById(md5);
//		if (fileInfo != null) {
//			fileInfoDao.update(fileInfo);
//			return fileInfo;
//		}
		String suffix = fileOrigName.substring(fileOrigName.lastIndexOf("."));
		String pathname = FileUtil.getPath() + md5 + suffix;
		String fullPath = filesPath + pathname;
		FileUtil.saveFile(file, fullPath);

		long size = file.getSize();
		String contentType = file.getContentType();

		fileInfo = new FileInfo();
		fileInfo.setId(md5);
		fileInfo.setContentType(contentType);
		fileInfo.setSize(size);
		fileInfo.setPath(fullPath);
		fileInfo.setUrl(pathname);
		fileInfo.setFilename(fileOrigName);
		fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);

		fileInfoDao.save(fileInfo);

		log.debug("上传文件{}", fullPath);

		return fileInfo;

	}

	@Override
	public void delete(String id) {
		FileInfo fileInfo = fileInfoDao.getById(id);
		if (fileInfo != null) {
			String fullPath = fileInfo.getPath();
			FileUtil.deleteFile(fullPath);

			fileInfoDao.delete(id);
			log.debug("删除文件：{}", fileInfo.getPath());
		}
	}

}
