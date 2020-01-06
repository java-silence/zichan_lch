package com.itycu.server.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.itycu.server.dao.FlowTodoItemDao;
import com.itycu.server.dao.TodoDao;
import com.itycu.server.dao.ZcBfItemDao;
import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.Todo;
import com.itycu.server.model.ZcBfItem;
import com.itycu.server.service.FileService;
import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.FileInfoDao;
import com.itycu.server.dto.LayuiFile;
import com.itycu.server.model.FileInfo;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.test.ImgUtil;
import com.itycu.server.test.PdfAddTextUtil;
import com.itycu.server.test.PdfUtil;
import com.itycu.server.utils.DateUtil;
import com.itycu.server.utils.ZiChanCodeUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.itycu.server.page.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "文件")
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${files.path}")
    private String filesPath;

    @Autowired
	private FileService fileService;

	@Autowired
	private FileInfoDao fileInfoDao;

	@Autowired
    private ZcBfItemDao zcBfItemDao;

	@Autowired
    private TodoDao todoDao;

	@Autowired
    private FlowTodoItemDao flowTodoItemDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file) throws IOException {
		try {
			return fileService.save(file);
		} catch (Exception e) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setCode(1);
			fileInfo.setMessage("文件不能超过100M");
			return fileInfo;
		}
	}

	/**
	 * layui富文本文件自定义上传
	 * 
	 * @param file
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	@LogAnnotation
	@PostMapping("/layui")
	@ApiOperation(value = "layui富文本文件自定义上传")
	public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
		FileInfo fileInfo = fileService.save(file);
		LayuiFile layuiFile = new LayuiFile();
		layuiFile.setCode(0);
		LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
		layuiFile.setData(data);
		data.setSrc(domain + "/statics" + fileInfo.getUrl());
		data.setTitle(file.getOriginalFilename());
		return layuiFile;
	}

	@GetMapping
	@ApiOperation(value = "文件查询")
	@PreAuthorize("hasAuthority('sys:file:query')")
	public PageTableResponse listFiles(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {
			@Override
			public int count(PageTableRequest request) {
				return fileInfoDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {
			@Override
			public List<FileInfo> list(PageTableRequest request) {
				List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "文件删除")
	@PreAuthorize("hasAuthority('sys:file:del')")
	public void delete(@PathVariable String id) {
		fileService.delete(id);
	}

	@GetMapping(params = {"bizid","biztype"})
	@ApiOperation(value = "根据业务表单id获取该表单所有的附件")
	public List<FileInfo> roles(Long bizid,String biztype) {
		return fileInfoDao.listBybizid(bizid,biztype);
	}

	@PostMapping("/muiUpload")
	public List<FileInfo> uploadImage( HttpServletRequest request) throws IOException  {

		List<FileInfo> fileInfos = new ArrayList<>();
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(
				request.getServletContext());
		if (cmr.isMultipart(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
			Iterator<String> files = mRequest.getFileNames();
			while (files.hasNext()) {
				MultipartFile mFile = mRequest.getFile(files.next());
				if (mFile != null) {
					fileInfos.add(fileService.save(mFile));
				}
			}
		}
		return fileInfos;
	}

    @PostMapping("/download")
    public String download(@RequestParam(value = "fileUrl",required = false) String filePath,
                           @RequestParam(value = "fileName",required = false) String fileName,
                           HttpServletRequest request, HttpServletResponse response){

        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            filePath = filePath.replace("..", "");
            if (filePath.endsWith(",")) {
                filePath = filePath.substring(0, filePath.length() - 1);
            }
            String localPath = filesPath;
            String downloadFilePath = localPath + filePath;
            File file = new File(downloadFilePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开            
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
                inputStream = new BufferedInputStream(new FileInputStream(file));
                outputStream = response.getOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                response.flushBuffer();
            }

        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 处置模块批量下载
     * @param flowTodoId
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/download2")
    public ResponseEntity<byte[]> download2(@RequestParam(value = "flowTodoId",required = false) Long flowTodoId,
                                            HttpServletRequest request) throws IOException {

        Todo todo = todoDao.getById(flowTodoId);
        List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(todo.getId());
        List<Long> bfItemIds = flowTodoItems.stream().map(e -> e.getFlowItemId()).collect(Collectors.toList());
        List<ZcBfItem> zcBfItemList = zcBfItemDao.listByIds(bfItemIds);
        ArrayList<String> bfFile = new ArrayList<>();
        ArrayList<String> identityFile = new ArrayList<>();
        ArrayList<String> damageFile = new ArrayList<>();
        //压缩后的文件
        String resourcesName = "ChuZhi"+DateUtil.getStringDate()+".zip";
        //String downPath = "C:/Users/Administrator/Downloads/";
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(filesPath+resourcesName));
        InputStream input = null;
        int i = 0;
        for (ZcBfItem zcBfItem : zcBfItemList) {

            String fileUrl = zcBfItem.getFileUrl();
            String identifyFileUrl = zcBfItem.getIdentifyFileUrl();
            String damagedFileUrl = zcBfItem.getDamagedFileUrl();
            if (!ObjectUtils.isEmpty(fileUrl) && !bfFile.contains(zcBfItem.getFileName())) {
                bfFile.add(zcBfItem.getFileName());
                String name = filesPath + fileUrl;
                input = new FileInputStream(new File(name));
                zipOut.putNextEntry(new ZipEntry("报废附件/"+zcBfItem.getFileName()));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }
            if (!ObjectUtils.isEmpty(identifyFileUrl) && !identityFile.contains(zcBfItem.getIdentifyFileName())) {
                identityFile.add(zcBfItem.getIdentifyFileName());
                String name = filesPath + identifyFileUrl;
                input = new FileInputStream(new File(name));
                zipOut.putNextEntry(new ZipEntry("鉴定附件/"+zcBfItem.getIdentifyFileName()));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }
            if (!ObjectUtils.isEmpty(damagedFileUrl) && !damageFile.contains(zcBfItem.getDamagedFileName())) {
                damageFile.add(zcBfItem.getDamagedFileName());
                String name = filesPath + damagedFileUrl;
                input = new FileInputStream(new File(name));
                zipOut.putNextEntry(new ZipEntry("报损附件/"+zcBfItem.getDamagedFileName()));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }

        }
        zipOut.close();
        File file = new File(filesPath+resourcesName);
        HttpHeaders headers = new HttpHeaders();
        String filename = new String(resourcesName.getBytes("utf-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    }

    /**
     * 合并下载
     * @param flowTodoId
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/download3")
    public void download3(@RequestParam(value = "flowTodoId",required = false) Long flowTodoId,
                          HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ImgUtil imageUtil = new ImgUtil();
        String path = "";
        try {
            Todo todo = todoDao.getById(flowTodoId);
            List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(todo.getId());
            List<Long> bfItemIds = flowTodoItems.stream().map(e -> e.getFlowItemId()).collect(Collectors.toList());
            List<ZcBfItem> zcBfItemList = zcBfItemDao.listByIds(bfItemIds);
            ArrayList<String> convertPicList = new ArrayList<>();
            ArrayList<String> files = new ArrayList<>();
            // ----------
            ArrayList<String> fileUrls = new ArrayList<>();
            ArrayList<String> identityFileUrls = new ArrayList<>();
            ArrayList<String> damageFileUrls = new ArrayList<>();
            ArrayList<String> allFiles = new ArrayList<>();
            // ----------
            for (int i = 0; i < zcBfItemList.size(); i++) {
                ZcBfItem zcBfItem = zcBfItemList.get(i);

                String zcName = zcBfItem.getZcName();
                String epcid = zcBfItem.getEpcid();
                String fileUrl = zcBfItem.getFileUrl();
                String identifyFileUrl = zcBfItem.getIdentifyFileUrl();
                String damagedFileUrl = zcBfItem.getDamagedFileUrl();
                // 报废附件
                if ( !ObjectUtils.isEmpty(fileUrl) ) {
                    if ( fileUrl.toLowerCase().endsWith(".png")|| fileUrl.toLowerCase().endsWith(".jpg")
                            || fileUrl.toLowerCase().endsWith(".gif") || fileUrl.toLowerCase().endsWith(".jpeg")
                            || fileUrl.toLowerCase().endsWith(".gif") ) {
                        // 转换图片
                        String file = filesPath+fileUrl;
                        BufferedImage bi = imageUtil.rotateImage(file);
                        if (bi==null)
                            continue;
                        String pdffile = PdfUtil.Img2PDF(file,bi,filesPath);
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(pdffile,pa,text);
                        //files.add(pdffile);
                        files.add(pa);
                        convertPicList.add(pdffile);
                        fileUrls.add(pdffile);
                    }else {
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(filesPath+fileUrl,pa,text);
                        // PDF文件
                        //files.add(filesPath+fileUrl);
                        files.add(pa);
                        fileUrls.add(filesPath+fileUrl);
                    }
                }
                // 鉴定附件
                if ( !ObjectUtils.isEmpty(identifyFileUrl) ) {
                    if ( identifyFileUrl.toLowerCase().endsWith(".png")|| identifyFileUrl.toLowerCase().endsWith(".jpg")
                            || identifyFileUrl.toLowerCase().endsWith(".gif") || identifyFileUrl.toLowerCase().endsWith(".jpeg")
                            || identifyFileUrl.toLowerCase().endsWith(".gif") ) {
                        // 转换图片
                        String file = filesPath+identifyFileUrl;
                        BufferedImage bi = imageUtil.rotateImage(file);
                        if (bi==null)
                            continue;
                        String pdffile = PdfUtil.Img2PDF(identifyFileUrl,bi,filesPath);
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(pdffile,pa,text);
                        files.add(pa);
                        convertPicList.add(pdffile);
                        identityFileUrls.add(pdffile);
                    }else {
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(filesPath+identifyFileUrl,pa,text);
                        // PDF文件
                        //files.add(filesPath+identifyFileUrl);
                        files.add(pa);
                        identityFileUrls.add(filesPath+identifyFileUrl);
                    }
                }
                // 报损附件
                if ( !ObjectUtils.isEmpty(damagedFileUrl) ) {
                    if ( damagedFileUrl.toLowerCase().endsWith(".png")|| damagedFileUrl.toLowerCase().endsWith(".jpg")
                            || damagedFileUrl.toLowerCase().endsWith(".gif") || damagedFileUrl.toLowerCase().endsWith(".jpeg")
                            || damagedFileUrl.toLowerCase().endsWith(".gif") ) {
                        // 转换图片
                        String file = filesPath+damagedFileUrl;
                        BufferedImage bi = imageUtil.rotateImage(file);
                        if (bi==null)
                            continue;
                        String pdffile = PdfUtil.Img2PDF(damagedFileUrl,bi,filesPath);
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(pdffile,pa,text);
                        files.add(pa);
                        convertPicList.add(pdffile);
                        damageFileUrls.add(pdffile);
                    }else {
                        // 添加文字
                        String pa = filesPath+System.currentTimeMillis()+ZiChanCodeUtil.random6()+".pdf";
                        String text = "【资产名称】："+zcName+"【资产追溯码】："+epcid;
                        PdfAddTextUtil.addPngWatermark(filesPath+damagedFileUrl,pa,text);
                        // PDF文件
                        //files.add(filesPath+damagedFileUrl);
                        files.add(pa);
                        damageFileUrls.add(filesPath+damagedFileUrl);
                    }
                }
            }
            // folder文件夹 mergeFileName 合成文件名称
            String folder = filesPath;
            String mergeFileName = System.currentTimeMillis()+".pdf";
            // 合并
            allFiles.addAll(fileUrls);
            allFiles.addAll(identityFileUrls);
            allFiles.addAll(damageFileUrls);
            //path = PdfUtil.getMergePDFStringList(allFiles, folder, mergeFileName);



            path = PdfUtil.getMergePDFStringList(files, folder, mergeFileName);
            // 删除合并的图片
            for (String file : convertPicList) {
                File file1 = new File(file);
                file1.delete();
            }
            File file = new File(path);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开            
                String fileName = "附件合并"+System.currentTimeMillis()+".pdf";
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
                inputStream = new BufferedInputStream(new FileInputStream(file));
                outputStream = response.getOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            File file = new File(path);
            if (file.isFile()) {
                file.delete();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
