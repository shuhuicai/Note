package com.service;

import com.util.OutputPDF;
import com.vo.NoteParamVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.util.Constant.filePath;

/**
 * @author CAIYUHUI
 * @create 2019/01/17 20:36
 **/
@Service("com.service.FileService")
public class FileService {

    /**
     * 保存图片到磁盘
     *
     * @param file     文件数据
     * @param fileType 文件类型
     * @return 成功时，返回文件保存在磁盘上的路径；失败时，返回null
     */
    public String saveFile(MultipartFile file, int fileType) throws Exception {
        if (file != null) {
            String fileName = file.getOriginalFilename();//文件上传过来的名字（包括后缀）
            String folderName;//根据文件类型分类到指定文件夹
            if (fileType == 0) {//图片
                folderName = "Images/";
            } else {//文档文件
                folderName = "Document/";
            }
            //文件保存的名字
            String saveFileName = String.valueOf(System.currentTimeMillis()) + fileName;
            String path = filePath + folderName + saveFileName;//文件保存的路径

            //转存文件到指定路径
            file.transferTo(new File(path));
            return path;
        }
        return null;
    }

    /**
     * 根据指定路径读取文件并响应到前端
     *
     * @param path     文件保存路径
     * @param response 响应Http请求
     * @throws Exception 异常
     */
    public void getFile(String path, HttpServletResponse response) throws Exception {
        File file = new File(path);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        }
    }

    /**
     * 将笔记转化为Html文件并保存
     *
     * @param note 笔记内容和名字
     * @return 返回文件保存的路径
     */
    public String noteToHtml(NoteParamVo note) {
        OutputPDF util = new OutputPDF(note.getContent(), note.getLabel());
        util.produceHtml();//保存
        return util.htmlPath();
    }
}
