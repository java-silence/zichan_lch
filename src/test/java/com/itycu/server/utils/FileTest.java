package com.itycu.server.utils;

import java.io.*;

public class FileTest {

    public static void main(String[] args) throws IOException {
        String data = "{\"ZcCheck\":{\"bh\":1,\"bz\":\"\",\"checkDeptId\":22,\"checkTime\":1579104000000,\"checkTimes\":0,\"checkUserId\":278,\"createBy\":278,\"createTime\":1579137455000,\"del\":0,\"id\":1,\"profit\":0,\"reCheck\":0,\"result\":0,\"status\":0,\"total\":17,\"updateBy\":0,\"updateTime\":1579137455000,\"zcCheckDownLoadItemVOList\":[{\"del\":0,\"epcid\":\"66400583\",\"glDeptId\":0,\"id\":1,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":583},{\"del\":0,\"epcid\":\"66400584\",\"glDeptId\":0,\"id\":2,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":584},{\"del\":0,\"epcid\":\"66400585\",\"glDeptId\":0,\"id\":3,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":585},{\"del\":0,\"epcid\":\"66400586\",\"glDeptId\":0,\"id\":4,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":586},{\"del\":0,\"epcid\":\"66400587\",\"glDeptId\":0,\"id\":5,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":587},{\"del\":0,\"epcid\":\"66400588\",\"glDeptId\":0,\"id\":6,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":588},{\"del\":0,\"epcid\":\"66400589\",\"glDeptId\":0,\"id\":7,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":589},{\"del\":0,\"epcid\":\"66400590\",\"glDeptId\":0,\"id\":8,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":590},{\"del\":0,\"epcid\":\"66400591\",\"glDeptId\":0,\"id\":9,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":591},{\"del\":0,\"epcid\":\"66400592\",\"glDeptId\":0,\"id\":10,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":592},{\"del\":0,\"epcid\":\"66400593\",\"glDeptId\":0,\"id\":11,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":593},{\"del\":0,\"epcid\":\"66400594\",\"glDeptId\":0,\"id\":12,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":594},{\"del\":0,\"epcid\":\"66400595\",\"glDeptId\":0,\"id\":13,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":595},{\"del\":0,\"epcid\":\"66400596\",\"glDeptId\":0,\"id\":14,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":596},{\"del\":0,\"epcid\":\"66400597\",\"glDeptId\":0,\"id\":15,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":597},{\"del\":0,\"epcid\":\"66400598\",\"glDeptId\":0,\"id\":16,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":598},{\"del\":0,\"epcid\":\"66400599\",\"glDeptId\":0,\"id\":17,\"profit\":0,\"reCheck\":0,\"result\":0,\"syDeptId\":0,\"zcCheckId\":1,\"zcId\":599}]}}";

        File file = File.createTempFile("aaaa",".json");

        FileOutputStream outputStream = new FileOutputStream(file.getPath(),true);
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();
//
//        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
//        byte[] buffer = new byte[fis.available()];
//        fis.read(buffer);
//        fis.close();
//        // 清空response
//        response.reset();
//        // 设置response的Header
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//        response.addHeader("Content-Length", "" + file.length());
//        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//        response.setContentType("application/octet-stream");
//        toClient.write(buffer);
//        toClient.flush();
//        toClient.close();
    }
}
