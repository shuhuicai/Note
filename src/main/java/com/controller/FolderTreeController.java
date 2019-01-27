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
 * 文件夹Controller
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
            ft.setRemarks("0");//该字段暂时作为前端文件重命名的一个判断条件，值为0，不会变的
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

    /**
     * 文件重命名
     *
     * @param ft (label  id)
     * @return true false
     */
    @RequestMapping(value = "/updateLabel", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateLabel(@RequestBody FolderTree ft) {
        try {
            return folderTreeService.updateLabel(ft.getId(), ft.getLabel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
