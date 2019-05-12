package com.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.util.Constant.filePath;

/**
 * 生成PDF文件 HTML文件
 *
 * @author CAIYUHUI
 * @create 2019/05/01 16:23
 **/
public class TransformFile {
    private String content;//笔记内容
    private String name;//笔记名

    public TransformFile(String content, String name) {
        this.content = content;
        this.name = name;
    }

    /**
     * 将笔记内容拼接成html文件
     */
    public void produceHtml() {
        String prefix = "<!DOCTYPE html><html><head><title>HTML to PDF</title></head><body>";
        String suffix = "</body></html>";
        String fileContent = prefix + content + suffix;
        fileContent = Jsoup.parse(fileContent).html();
        try (FileOutputStream fos = new FileOutputStream(htmlPath())) {
            fos.write(fileContent.getBytes(StandardCharsets.UTF_8));
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

            MyFontsProvider fontsProvider = new MyFontsProvider();
            fontsProvider.addFontSubstitute("lowagie", "garamond");
            fontsProvider.setUseUnicode(true);
            CssAppliers cssAppliers = new CssAppliersImpl(fontsProvider);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath()), Charset.forName("utf-8"), fontsProvider);
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

    /**
     * 重写 字符设置方法，解决中文乱码问题
     */
    public static class MyFontsProvider extends XMLWorkerFontProvider {

        public MyFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
            String fntname = fontname;
            if (fntname == null) {
                fntname = "宋体";
            }
            if (size == 0) {
                size = 4;
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

}
