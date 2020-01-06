package com.itycu.server.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pdf添加文字
 * @author lch
 * @create 2019-12-27 21:14
 */
public class PdfAddTextUtil {

    public static boolean addPngWatermark(String InPdfFile, String outPdfFile , String textWatermark) throws IOException, DocumentException {

        try{
            PdfReader reader = new PdfReader(InPdfFile);
            //reader.unethicalreading = true;
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outPdfFile));
            //这里的字体设置比较关键，这个设置是支持中文的写法
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.EMBEDDED);// 使用系统字体
            int total = reader.getNumberOfPages();
            //文字的长度
            int l= textWatermark.length();
            int size=0;
            if(l<=6){
                size=22;
            }
            if(l>6 && l<=11){
                size=15;
            }
            if(l>11){
                size=10;
            }
            if(l>15){
                size=5;
            }
            Font f= new Font(base,15);
            Phrase p = new Phrase(textWatermark, f);
            // transparency
            PdfGState gs1 = new PdfGState();
            // 设置水印透明度
            gs1.setFillOpacity(0.6f);
            PdfContentByte over;
            Rectangle pagesize;
            for (int i = 1; i <=total; i++) {
                pagesize = reader.getPageSizeWithRotation(i);
                over = stamper.getOverContent(i);
                over.saveState();
                over.setGState(gs1);
                over.setTextMatrix(30, 30);
                over.setColorFill(BaseColor.BLACK);
                ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 230, 30, 0);
//                for (int y = 0; y < 10; y++) {
//                    for (int x = 0; x < 8; x++) {
//                        // 水印文字成45度角倾斜
//                        ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 80 + 140 * x, 158 * y, -45);
//                    }
//                }
                over.restoreState();
            }
            stamper.close();
            reader.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
