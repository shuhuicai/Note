package com.service;

import com.dao.FolderTreeMapper;
import com.entity.FolderTree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FolderTree业务逻辑
 *
 * @author CAIYUHUI
 * @create 2019/01/05 16:21
 **/
@Service("com.service.FolderTreeService")
public class FolderTreeService {

    @Resource(name = "com.dao.FolderTreeMapper")
    private FolderTreeMapper folderTreeMapper;

    /**
     * 初始化时，查询出整个目录结构
     *
     * @return 返回查询结果
     */
    public List<FolderTree> getAllFolder() {
        //先查找所有根目录(id值为0)
        List<FolderTree> res = queryFolder("0");
        return res;
    }

    /**
     * 递归查询所有的目录
     *
     * @param id 所有根目录的id值
     * @return 返回查询结果
     */
    private List<FolderTree> queryFolder(String id) {
        List<FolderTree> res = folderTreeMapper.findFolderByParentId(id);
        if (res != null) {//存在目录
            for (FolderTree ft : res) {
                if (ft.getIsFolder() == 1) {//如果为文件夹，则继续递归查询子子孙孙目录
                    List<FolderTree> childrenList = queryFolder(ft.getId());
                    if (childrenList != null) {
                        ft.setChildren(childrenList);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 创建文件夹或文件
     *
     * @param ft 文件夹或文件参数
     * @return 返回创建成功与否
     */
    public boolean createFolderOrFile(FolderTree ft) throws Exception {
        return folderTreeMapper.insert(ft) > 0;
    }

    /**
     * 删除指定id值的目录
     *
     * @param ft 要删除的文件夹或文件信息
     * @return 返回删除成功与否布尔值
     * @throws Exception 异常
     */
    public boolean deleteFolderTree(FolderTree ft) throws Exception {
        String[] ids = getIds(ft);
        return folderTreeMapper.deleteFolderTreeById(ids) > 0;
    }

    /**
     * 从FolderTree对象中获取Id值
     *
     * @param ft 参数
     * @return 返回id组成的数组
     */
    private String[] getIds(FolderTree ft) {
        List<String> ids = new ArrayList<>();
        FolderTree tmp;
        ids.add(ft.getId());
        if (ft.getChildren() != null) {
            getIdFromChildren(ids, ft.getChildren());
        }

        return ids.toArray(new String[ids.size()]);
    }

    /**
     * 遍历children里的每一个FolderTree对象，获取id值
     *
     * @param ids      存放id值的集合
     * @param children FolderTree集合
     */
    private void getIdFromChildren(List<String> ids, List<FolderTree> children) {
        for (FolderTree ft : children) {
            ids.add(ft.getId());
            if (ft.getChildren() != null) {
                getIdFromChildren(ids, ft.getChildren());
            }
        }
    }
}
