package com.itycu.server.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.util.PDFMergerUtility;
import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfUtil {


    /**
     * @Author Ragty
     * @Description 将图片转换为PDF
     * @Date 15:27 2019/3/4
     * @return
     **/
    public static String Img2PDF(String imagePath,BufferedImage img,String descfolder) throws Exception{
        String pdfPath = "";
        try {
            //图片操作
            Image image = null;
            File file = new File(descfolder);

            if (!file.exists()){
                file.mkdirs();
            }

            pdfPath = descfolder +"/ZH"+System.currentTimeMillis()+".pdf";
            String type = imagePath.substring(imagePath.lastIndexOf(".")+1);
            Document doc = new Document(null, 0, 0, 0, 0);

            //更换图片图层
            BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
            bufferedImage.getGraphics().drawImage(img, 0,0, img.getWidth(), img.getHeight(), null);
            bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB),null).filter (bufferedImage,null);

            //图片流处理
            doc.setPageSize(new Rectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));
            System.out.println(bufferedImage.getWidth()+"()()()()()"+bufferedImage.getHeight());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(bufferedImage, type, out);
            byte[] b = out.toByteArray();
            image = Image.getInstance(b);

            //写入PDF
            System.out.println("写入PDf:" + pdfPath);
            FileOutputStream fos = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.add(image);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return pdfPath;
    }

    /**
     * @Author Ragty
     * @Description 获取文件夹下的PDF
     * @Date 17:33 2019/3/7
     * @return
     **/
    private static String[] getFiles(String folder) throws IOException {
        File _folder = new File(folder);
        String[] filesInFolder;

        if (_folder.isDirectory()) {
            filesInFolder = _folder.list();
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }

    /**
     * @Author Ragty
     * @Description 合成PDF
     * @Date 17:25 2019/3/7
     * @return
     **/
    public static void mergePDF(String[] files,String desfolder,String mergeFileName ) throws Exception{

        PDFMergerUtility mergePdf = new PDFMergerUtility();

        for (String file :files) {
            if (file.toLowerCase().endsWith("pdf"))
                mergePdf.addSource(file);
        }

        mergePdf.setDestinationFileName(desfolder+"/"+mergeFileName);
        mergePdf.mergeDocuments();
        System.out.println("merge over");

    }

    /**
     * 合并pdf文件并返回路径
     * @param files
     * @param desfolder
     * @param mergeFileName
     * @return
     * @throws Exception
     */
    public static String getMergePDFString (String[] files,String desfolder,String mergeFileName ) throws Exception{
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (String file :files) {
            if (file.toLowerCase().endsWith("pdf"))
                mergePdf.addSource(file);
        }
        String path = desfolder+"/"+mergeFileName;
        mergePdf.setDestinationFileName(path);
        mergePdf.mergeDocuments();
        System.out.println("merge over");
        return path;
    }

    /**
     * 合并pdf文件并返回路径
     * @param files   更改为List
     * @param desfolder
     * @param mergeFileName
     * @return
     * @throws Exception
     */
    public static String getMergePDFStringList (List<String> files, String desfolder, String mergeFileName ) throws Exception{
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (String file :files) {
            if (file.toLowerCase().endsWith("pdf"))
                mergePdf.addSource(file);
        }
        String path = desfolder+"/"+mergeFileName;
        mergePdf.setDestinationFileName(path);
        mergePdf.mergeDocuments();
        System.out.println("merge over");
        return path;
    }

    public static void main(String[] args) throws Exception{

        ImgUtil imageUtil = new ImgUtil();
        String[] files = new String[4];
        files[0] = "D:/test/1.jpg";
        files[1] = "D:/test/2.jpg";
        files[2] = "D:/test/contract.pdf";
        files[3] = "D:/test/ISO9001质量管理体系认证证书.pdf";

        String folder = "D:/test";
        String mergeFileName = "final.pdf";
        for (int i=0;i<files.length;i++) {
            String file = files[i];
            if (file.toLowerCase().endsWith(".png")|| file.toLowerCase().endsWith(".jpg")
                || file.toLowerCase().endsWith(".gif") || file.toLowerCase().endsWith(".jpeg")
                || file.toLowerCase().endsWith(".gif") ) {
                BufferedImage bi = imageUtil.rotateImage(file);
                if (bi==null)
                    continue;
                String pdffile = Img2PDF(file,bi,folder);
                files[i] = pdffile;
            }
        }
        mergePDF(files,folder,mergeFileName);
    }

}
