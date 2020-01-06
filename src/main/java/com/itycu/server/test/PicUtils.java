//package com.itycu.server.test;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//
//public class PicUtils {
//
//    /**
//     * 在图片上添加文字和图片
//     *
//     * @param companyName
//     * @param position
//     * @param baseMap
//     * @param firstImg
//     * @param savePath
//     */
//    public static boolean exportImg(String companyName, String position, String baseMap, String firstImg,
//            String savePath) {
//        boolean flag = false;
//        try {
//            // baseMap是你的 主图片的路径
////            InputStream is = new FileInputStream(baseMap);
////            // 通过JPEG图象流创建JPEG数据流解码器
////            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
////            // 解码当前JPEG数据流，返回BufferedImage对象
////            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
//            File _img_file_ = new File(baseMap);
//            BufferedImage buffImg = ImageIO.read(_img_file_);
//            BigDecimal height = BigDecimal.valueOf(buffImg.getHeight());
//            BigDecimal width = BigDecimal.valueOf(buffImg.getWidth());
//
//            // 得到画笔对象
//            Graphics g = buffImg.getGraphics();
//
//            // 创建你要附加的图象。
//            // 小图片的路径
//            ImageIcon imgIcon = new ImageIcon(firstImg);
//
//            // 得到Image对象。
//            Image img = imgIcon.getImage();
//
//            // 将小图片绘到大图片上，并定义图片的长和宽。
//            //g.drawImage(img, 71, 489, 603, 450, null);
//
//            // 设置颜色。
//            g.setColor(Color.BLACK);
//
//            // 最后一个参数用来设置字体的大小
//            Font f = new Font("微软雅黑", Font.PLAIN, 30);
//            Color mycolor = Color.white;// new Color(0, 0, 255);
//            g.setColor(mycolor);
//            g.setFont(f);
//
//            // 这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//            g.drawString(companyName, 60, 60);
//
//            // 这段文字在图片上的位置(x,y) .第二个是你设置的内容。
//            //g.drawString(position, 20, 20);
//
//            g.dispose();
//
//            OutputStream os;
//
//            // 具体的路径以及文件名字，例如d:/union.jpg
//            os = new FileOutputStream(savePath);
//            // 创键编码器，用于编码内存中的图象数据。
//            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//            en.encode(buffImg);
//
//            //is.close();
//            os.close();
//
//            flag = true;
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    public static void main(String[] args) throws ParseException {
//        boolean exportImg = exportImg("广州啦啦啦啦啦有限公司", "展位号-4.1馆K51",
//                "d:/test/1.jpg", "d:/test/2.jpg", "d:/test/union.jpg");
//        System.out.println("是否合成成功：" + exportImg);
//    }
//}
