package com.controller;

import com.entity.FolderTree;
import com.service.FolderTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件夹Controllerr
 *
 * @author CAIYUHUI
 * @create 2019/01/05 22:06
 **/
@Controller("com.controller.FolderTreeController")
@RequestMapping("/folder")
public class FolderTreeController {
    @Resource(name = "com.service.FolderTreeService")
    private FolderTreeService folderTreeService;

    /**
     * 初始化文件目录结构
     * 获取所有的文件夹
     *
     * @return 返回查询结果
     */
    @RequestMapping(value = "/initFolder", method = RequestMethod.POST)
    @ResponseBody
    public List<FolderTree> initFolder() {
        return folderTreeService.getAllFolder();
    }

    /**
     * 创建新目录
     *
     * @param ft 新目录
     * @return 返回该新目录对象
     */
    @RequestMapping(value = "/createFolder", method = RequestMethod.POST)
    @ResponseBody
    public FolderTree createFolder(@RequestBody FolderTree ft) {
        try {
            if (folderTreeService.createFolderOrFile(ft)) {
                return ft;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除指定目录
     * 当存在子文件夹或文件时，也同样删除
     *
     * @param ft 要删除的文件夹或文件信息
     * @return 返回删除成功与否布尔值
     */
    @RequestMapping(value = "/deleteFolder", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteFolder(@RequestBody FolderTree ft) {
        try {
            return folderTreeService.deleteFolderTree(ft);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
