package com.controller;

import com.bean.ResultBean;
import com.entity.FileUrlMapping;
import com.entity.FolderTree;
import com.service.FileUrlMappingService;
import com.service.FolderTreeService;
import com.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.util.Constant.localIP;
import static com.util.RandomName.getName;

/**
 * @author CAIYUHUI
 * @create 2018/12/22 23:14
 **/
@Controller("com.controller.FileController")
@RequestMapping("/file")
public class FileController {

    @Resource(name = "com.service.FileService")
    private FileService fileService;

    @Resource(name = "com.service.FileUrlMappingService")
    private FileUrlMappingService fileUrlMappingService;

    @Resource(name = "com.service.FolderTreeService")
    private FolderTreeService folderTreeService;

    /**
     * 保存图片
     *
     * @param file     要保存的图片文件
     * @param parentId 该图片属于哪个文件夹
     * @param fileType 文件类型
     * @return 返回该图片所在的目录信息
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<FolderTree> saveFile(@RequestBody MultipartFile file, String parentId, String fileType) {
        String fileName = file.getOriginalFilename();
        FolderTree ft = new FolderTree();
        ResultBean<FolderTree> res = new ResultBean<>();

        String path = null;
        try { //先将文件保存到磁盘
            path = fileService.saveFile(file, fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (path != null) {//不为空表示保存成功，生成外界访问的URL
            String visitUrl = localIP + getName(fileName);

            //将磁盘路径path和访问URL保存到数据库映射表
            FileUrlMapping mapping = new FileUrlMapping();
            mapping.setVisitUrl(visitUrl);
            mapping.setDiskUrl(path);

            //将文件以文件夹的形式向folderTree数据库表添加记录
            ft.setIsFolder(0);
            ft.setLabel(fileName);
            ft.setParentId(parentId);
            ft.setFileUrl(visitUrl);

            switch (fileType) {
                case "image":
                    ft.setFileType(0);//图片类型
                    break;
                case "pdf":
                    ft.setFileType(1);//pdf
                    break;
                case "word":
                    ft.setFileType(2);//word
                    break;
            }
            try {
                fileUrlMappingService.addUrl(mapping);
                folderTreeService.createFolderOrFile(ft);
                res.setResult(1);//成功
                res.setData(ft);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.setResult(0);
        return res;
    }

    /**
     * 显示图片
     *
     * @param visitURL 文件给外界访问的链接
     * @param response 响应
     */
    @RequestMapping(value = "/showFile", method = {RequestMethod.POST, RequestMethod.GET})
    public void showFile(String visitURL, HttpServletResponse response) {
        //根据URL到映射表查询出文件存储的地址
        String path = fileUrlMappingService.queryUrl(visitURL);
        //根据得到路径读取文件
        try {
            if (path != null) {
                fileService.getFile(path, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
