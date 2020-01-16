package com.itycu.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.itycu.server.dao.ZcCheckDao;
import com.itycu.server.dao.ZcCheckItemDao;
import com.itycu.server.model.ZcCheckDownLoadItemVO;
import com.itycu.server.model.ZcCheckDownLoadVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RequestMapping(value = "/zcCheck")
@Controller
public class ZcCheckUpLoadController {

    private static Logger logger = LoggerFactory.getLogger(ZcCheckUpLoadController.class);

    @Autowired
    private ZcCheckDao zcCheckDao;

    @Autowired
    private ZcCheckItemDao zcCheckItemDao;

    @PostMapping(value = "/download")
    @ApiOperation(value = "导出创建的盘点单数据", notes = "导出的创建盘点单的数据")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = (request.getParameter("downId"));
            logger.info("当前导出的数据是{}", id);
            ZcCheckDownLoadVO zcCheck = zcCheckDao.getZcInfoDownLoadById(Long.parseLong(id));
            if (null != zcCheck) {
                List<ZcCheckDownLoadItemVO> zcCheckItemsList = zcCheckItemDao.getZcInfoDownLoadItemById(Long.parseLong(id));
                if (!CollectionUtils.isEmpty(zcCheckItemsList)) {
                    zcCheck.setZcCheckDownLoadItemVOList(zcCheckItemsList);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ZcCheck", zcCheck);
                    logger.info("获得的数据是{}", jsonObject.toString());
                    String result = jsonObject.toString();
                    exportJsonData(result, response);
                }
            } else {
                logger.error("盘点单的数据不存在");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void exportJsonData(String data, HttpServletResponse response) throws IOException {
        File file = File.createTempFile("PanDian", ".json");
        FileOutputStream outputStream = new FileOutputStream(file.getPath(), true);
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes(), "utf-8"));
        //获取文件输入流
        InputStream in = new FileInputStream(file.getPath());
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
    }
}
