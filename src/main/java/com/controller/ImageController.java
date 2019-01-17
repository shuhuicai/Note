package com.controller;

import com.entity.FileUrlMapping;
import com.entity.FolderTree;
import com.service.FileUrlMappingService;
import com.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.util.Constant.localIP;

/**
 * @author CAIYUHUI
 * @create 2018/12/22 23:14
 **/
@Controller("com.controller.ImageController")
@RequestMapping("/image")
public class ImageController {

    @Resource(name = "com.service.ImageService")
    private ImageService imageService;

    @Resource(name = "com.service.FileUrlMappingService")
    private FileUrlMappingService fileUrlMappingService;

    /**
     * 保存图片
     *
     * @param file     上传的文件
     * @param request  http请求
     * @param response http响应
     * @return 返回
     */
    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    @ResponseBody
    public String saveImage(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletRequest response) {
        FolderTree ft = new FolderTree();//后期会修改去掉

        String path = null;
        try { //先将文件保存到磁盘
            path = imageService.saveImage(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (path != null) {//不为空表示保存成功，生成外界访问的URL
            String visitUrl = localIP + "";

            //将磁盘路径path和访问URL保存到数据库映射表
            FileUrlMapping mapping = new FileUrlMapping();
            mapping.setVisitUrl(visitUrl);
            mapping.setDiskUrl(path);

            try {
                fileUrlMappingService.addUrl(mapping);
                //将文件以文件夹的形式向folderTree数据库添加记录

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return "";
    }

    /**
     * 显示图片
     *
     * @param visitUrl 图片链接
     * @param response 响应请求
     */
    @RequestMapping(value = "showImage/{visitUrl}", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadImage(@PathVariable("visitUrl") String visitUrl, HttpServletResponse response) {
        //根据URL到映射表查询出文件存储的地址
        String path = fileUrlMappingService.queryUrl(visitUrl);
        //根据得到路径读取文件
        try {
            imageService.getImage(path, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
