package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.FolderTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * FolderTreeMapper
 *
 * @author CAIYUHUI
 * @create 2019/01/05 14:57
 **/
@Repository("com.dao.FolderTreeMapper")
public interface FolderTreeMapper extends BaseMapper<FolderTree> {

    /**
     * 获取所有存在的文件、文件夹数据（无树结构）
     *
     * @param page 分页查询
     * @return 返回查询结果
     */
    List<FolderTree> findAllFolder(Pagination page);

    /**
     * 根据父目录id值查找出所有的子目录
     * 有树结构的查询
     *
     * @param parentId 父目录id值
     * @return 返回所有子目录
     */
    List<FolderTree> findFolderByParentId(@Param(value = "parentId") String parentId, @Param(value = "creator") String creator);

    /**
     * 逻辑删除指定id的目录
     *
     * @param ids 要删除的目录id值
     * @return 返回删除成功与否
     */
    int deleteFolderTreeById(@Param("ids") String[] ids, @Param("modifier") String modifier);

    /**
     * 根据id值更新指定的文件名称label
     *
     * @param map 参数id,label
     * @return 返回成功与否
     */
    int updateLabelById(Map<String, String> map);

    /**
     * 查询具有该标签的笔记
     *
     * @param creator
     * @param tagContent 标签内容
     * @return
     */
    List<FolderTree> findNoteByTag(@Param("creator") String creator, @Param("tagContent") String tagContent);
}
