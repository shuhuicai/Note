package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.util.Constant.filePath;

/**
 * @author CAIYUHUI
 * @create 2019/01/17 20:36
 **/
@Service("com.service.ImageService")
public class ImageService {

    /**
     * 保存图片到磁盘
     *
     * @param file 图片文件数据
     * @return 成功时，返回图片保存在磁盘上的路径；失败时，返回null
     */
    public String saveImage(MultipartFile file) throws Exception {
        if (file != null) {
            String fileName = file.getOriginalFilename();//文件上传过来的名字（包括后缀）
            //获取文件类型（后缀）
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {
                //只支持gif,png,jpg格式的文件
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    //文件保存的名字
                    String saveFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    String path = filePath + "\\Images\\" + saveFileName;//文件保存的路径

                    //转存文件到指定路径
                    file.transferTo(new File(path));
                    return path;

                } else {
                    //格式不符合条件
                }
            } else {
                //文件类型为空
            }
        }
        return null;
    }

    /**
     * 根据指定路径读取图片
     *
     * @param path     文件保存路径
     * @param response 响应Http请求
     * @throws Exception 异常
     */
    public void getImage(String path, HttpServletResponse response) throws Exception {
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
}
