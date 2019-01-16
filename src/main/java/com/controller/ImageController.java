package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.util.Constant.filePath;

/**
 * @author CAIYUHUI
 * @create 2018/12/22 23:14
 **/
@Controller("com.controller.ImageController")
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    @ResponseBody
    public String saveImage(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletRequest response) {
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
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //格式不符合条件
                }
            } else {
                //文件类型为空

            }
        }
        return "";
    }

    /**
     * 图片上传
     *
     * @param file     接收的文件
     * @param request  Http请求
     * @param response Http响应
     * @return 返回图片存放的路径
     */
    @RequestMapping(value = "/testImage", method = RequestMethod.POST)
    @ResponseBody
    public String testImage(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("上传的图片是否为空：" + file.isEmpty());
        if (file != null) {
            String path = null;//文件路径
            String imgType;//图片类型
            String fileName = file.getOriginalFilename();//原文件名称
            //判断图片类型
            imgType = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (imgType != null) {
                if ("GIF".equals(imgType.toUpperCase()) || "PNG".equals(imgType.toUpperCase()) || "JPG".equals(imgType.toUpperCase())) {
                    String realPath = "E:\\WorkSpace\\Images\\";
                    //自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    //设置图片存放的路径
                    path = realPath + trueFileName;
                    System.out.println("图片存放路径为：" + path);
                    //转存文件到指定路径
                    try {
                        file.transferTo(new File(path));
                        System.out.println("文件成功上传到指定目录下");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("请上传GIF，PNG，或者JPG格式的文件");
                }
            } else {
                System.out.println("文件类型为空");
            }
            return path;
        } else {
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为" + file.getOriginalFilename());
        return "";
    }

    /**
     * 显示图片
     *
     * @param fileName 图片文件名
     * @param suffix   图片文件名后缀
     * @param response Http响应
     */
    @RequestMapping(value = "showImage/{fileName}.{suffix}", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadImage(@PathVariable("fileName") String fileName, @PathVariable("suffix") String suffix, HttpServletResponse response) {
        String path = "E:\\WorkSpace\\Images\\" + fileName + "." + suffix;
        File file = new File(path);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];//缓存区
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
