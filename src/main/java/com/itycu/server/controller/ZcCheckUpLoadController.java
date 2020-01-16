package com.itycu.server.controller;


import com.alibaba.fastjson.JSON;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.dao.ZcCheckDao;
import com.itycu.server.dao.ZcCheckItemDao;
import com.itycu.server.model.ZcCheck;
import com.itycu.server.model.ZcCheckDownLoadItemVO;
import com.itycu.server.model.ZcCheckDownLoadVO;
import com.itycu.server.model.ZcCheckItem;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ZcCheck zcCheck = zcCheckDao.getZcInfoDownLoadById(Long.parseLong(id));
            if (null != zcCheck) {
                List<ZcCheckItem> zcCheckItemsList = zcCheckItemDao.getZcInfoDownLoadItemById(Long.parseLong(id));
                if (!CollectionUtils.isEmpty(zcCheckItemsList)) {
                    zcCheck.setCheckItemList(zcCheckItemsList);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ZcCheck", zcCheck);
                    logger.info("获得的数据是{}", jsonObject.toString());
                    String result = jsonObject.toString();
                    exportJsonData(JSON.toJSONString(zcCheck), response);
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


    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传盘点之后的结果数据", notes = "上传盘点之后的结果数据")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return FailMap.createFailMapMsg("上传失败，请选择文件");
        }
        InputStream inputStream = file.getInputStream();
        StringBuffer buffer = new StringBuffer();
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        inputStream.close();
        logger.info("读取到Json数据是{}", buffer.toString());
        return updateUploadData(buffer.toString());
    }


    @Transactional(rollbackFor = Exception.class)
    Map<String, Object> updateUploadData(String data) {
        Map<String, Object> map = new HashMap<>();
        JSONObject egJo = JSONObject.fromObject(data);
        Map classMap = new HashMap();
        /**
         * TODO
         */
        classMap.put("zcCheckItemList",String.class);
        com.itycu.server.model.ZcCheck zcCheckDownLoadVO = (ZcCheck) JSONObject.toBean(egJo, com.itycu.server.model.ZcCheck.class);
        List<com.itycu.server.model.ZcCheckItem> loadItemVOList = zcCheckDownLoadVO.getCheckItemList();
        if (!CollectionUtils.isEmpty(loadItemVOList)) {
            loadItemVOList.forEach(k->{
                System.out.println((ZcCheckItem)k);
            });
            zcCheckItemDao.updateLoadCheckIem(loadItemVOList);
            int result = zcCheckDao.update(zcCheckDownLoadVO);
            if (result > 0) {
                map.put("code", 0);
                map.put("message", "操作成功");
                map.put("data", null);
                return map;
            } else {
                return FailMap.createFailMapMsg("导入失败");
            }
        } else {
            return FailMap.createFailMapMsg("导入数据失败");
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
