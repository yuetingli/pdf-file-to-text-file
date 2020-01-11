package com.bee.web.controller.test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PdfToTextFileUtils {
    public static void main(String[] args) throws IOException {
        pdfToTextFile("D:\\阿里巴巴Java开发手册（公开版）.pdf", "D:\\Java开发手册3.txt");
    }

    /**
     * PDF格式的文件转为text格式的文件工具类
     *
     * @param pdfFileUrl
     * @param saveTextFileUrl
     */
    public static void pdfToTextFile(String pdfFileUrl, String saveTextFileUrl) {

       /* 注:需要加依赖包如下:
         * <dependency>
         <groupId>org.apache.pdfbox</groupId>
         <artifactId>pdfbox</artifactId>
         <version>2.0.4</version>
         </dependency>*/

        try {
            // PDF格式的文件绝对路径[D:\阿里巴巴Java开发手册（公开版）.pdf]
            File file = new File(pdfFileUrl);
            PDDocument doc = PDDocument.load(file);
            // 总页数
            int pages = doc.getNumberOfPages();
            System.out.print("pages" + pages + "\r\n");
            // 转换为text文件存放的绝对路径[D:\Java开发手册（公开版）.txt]
            FileOutputStream fos = new FileOutputStream(saveTextFileUrl);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            // 排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(pages);
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
        } catch (IOException e) {
            System.out.println("pdf格式转换为text格式的失败,原因如异常:" + e);
        }
    }
}
