package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.DataBean;
import com.dao.FolderTreeMapper;
import com.entity.FolderTree;
import com.util.SessionUtil;
import com.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取所有目录（无树结构的）
     *
     * @return 返回查询结果
     */
    public DataBean queryAllFolder(UserVo userVo) {
        Page<FolderTree> page = new Page<>();
        if (userVo.getPageSize() > 0) {
            page.setSize(userVo.getPageSize());
        } else {
            page.setSize(5);
        }

        if (userVo.getPage() > 0) {
            page.setCurrent(userVo.getPage());
        } else {
            page.setCurrent(1);
        }
        DataBean<FolderTree> dataBean = new DataBean<>();
        dataBean.setLists(folderTreeMapper.findAllFolder(page));
        dataBean.setIndex(page.getCurrent());
        dataBean.setPages(page.getPages());
        dataBean.setTotal(page.getTotal());
        dataBean.setPageSize(page.getSize());
        return dataBean;
    }

    /**
     * 初始化时，查询出整个目录结构（有树结构的）
     *
     * @return 返回查询结果
     */
    public List<FolderTree> getAllFolder(HttpServletRequest request) {
        String account = SessionUtil.getCurrentUser(request);
        if (account == null) {
            account = "#";
        }
        //先查找所有根目录(id值为0)
        return queryFolder("0", account);
    }

    /**
     * 递归查询所有的目录
     *
     * @param id 所有根目录的id值
     * @return 返回查询结果
     */
    private List<FolderTree> queryFolder(String id, String account) {
        List<FolderTree> res = folderTreeMapper.findFolderByParentId(id, account);
        if (res != null) {//存在目录
            for (FolderTree ft : res) {
                if (ft.getIsFolder() == 1) {//如果为文件夹，则继续递归查询子子孙孙目录
                    List<FolderTree> childrenList = queryFolder(ft.getId(), account);
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
    public boolean createFolderOrFile(FolderTree ft, HttpServletRequest request) throws Exception {
        String username = SessionUtil.getCurrentUser(request);
        if (username == null) {
            username = "#";
        }
        ft.setCreator(username);
        ft.setModifier(username);
        return folderTreeMapper.insert(ft) > 0;
    }

    /**
     * 删除指定id值的目录
     *
     * @param ft 要删除的文件夹或文件信息
     * @return 返回删除成功与否布尔值
     * @throws Exception 异常
     */
    public boolean deleteFolderTree(FolderTree ft, HttpServletRequest request) throws Exception {
        String[] ids = getIds(ft);
        return folderTreeMapper.deleteFolderTreeById(ids, SessionUtil.getCurrentUser(request)) > 0;
    }

    /**
     * 重命名文件
     *
     * @param id    要重命名的文件的id值
     * @param label 更新后的名字
     * @return 返回成功与否
     */
    public boolean updateLabel(String id, String label, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("label", label);
        map.put("modifier", SessionUtil.getCurrentUser(request));
        return folderTreeMapper.updateLabelById(map) > 0;
    }

    /**
     * 从FolderTree对象中获取Id值
     *
     * @param ft 参数
     * @return 返回id组成的数组
     */
    private String[] getIds(FolderTree ft) {
        List<String> ids = new ArrayList<>();
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
