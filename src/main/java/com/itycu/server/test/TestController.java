package com.itycu.server.test;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lch
 * @create 2019-12-25 8:38
 */
public class TestController {


    public static void main(String args[]) throws IOException, DocumentException {
        String fileName = "D:/test/ISO9001质量管理体系认证证书.pdf"; // pdf模板
        InputStream input = new FileInputStream(new File(fileName));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:/test/contract.pdf"));
        AcroFields form = stamper.getAcroFields();
        fillData(form, data());
        stamper.setFormFlattening(true);

        Image image = Image.getInstance("D:/test/开户许可证.JPG");
        image.scaleToFit(100, 125);
        PdfContentByte content=null;
        int pageCount=reader.getNumberOfPages();//获取PDF页数
        System.out.println("pageCount="+pageCount);
        content =stamper.getOverContent(pageCount);
        image.setAbsolutePosition(100, 700);
        content.addImage(image);
        stamper.close();
        reader.close();
    }

    public static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            System.out.println(key+"= "+fields.getField(key)+" value="+value);
            fields.setField(key, value);
        }
    }

    public static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("trueName", "xxxxxx");
        data.put("idCard", "xxxxxx");
        data.put("userName", "xxxx");
        data.put("address", "12");
        data.put("telephone", "123456");
        data.put("signName","xxx");
        return data;
    }
}
