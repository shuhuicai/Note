package com.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.util.Constant.filePath;

/**
 * 生成PDF文件 HTML文件
 *
 * @author CAIYUHUI
 * @create 2019/05/01 16:23
 **/
public class OutputPDF {
    private String content;//笔记内容
    private String name;//笔记名

    public OutputPDF(String content, String name) {
        this.content = content;
        this.name = name;
    }

    /**
     * 将笔记内容拼接成html文件
     */
    public void produceHtml() {
        String prefix = "<!DOCTYPE html><html><head><meta charset='utf-8' /><title>HTML to PDF</title></head><body>";
        String suffix = "</body></html>";
        try (FileOutputStream fos = new FileOutputStream(htmlPath())) {
            fos.write((prefix + content + suffix).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将html文件转化为pdf文件
     */
    public void produce() {
        produceHtml();
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(pdfPath())));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath()), Charset.forName("utf-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            document.close();
        }
    }

    /* 生成PDF和HTML文件的输出路径 */
    public String pdfPath() {
        return filePath + "output/" + name + ".pdf";
    }

    public String htmlPath() {
        return filePath + "output/" + name + ".html";
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     * @return 成功与否
     */
    public boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }
}
