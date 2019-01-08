package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.FolderTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FolderTreeMapper
 *
 * @author CAIYUHUI
 * @create 2019/01/05 14:57
 **/
@Repository("com.dao.FolderTreeMapper")
public interface FolderTreeMapper extends BaseMapper<FolderTree> {

    /**
     * 根据父目录id值查找出所有的子目录
     *
     * @param parentId 父目录id值
     * @return 返回所有子目录
     */
    List<FolderTree> findFolderByParentId(@Param(value = "parentId") String parentId);

    /**
     * 逻辑删除指定id的目录
     *
     * @param ids 要删除的目录id值
     * @return 返回删除成功与否
     */
    int deleteFolderTreeById(String[] ids);
}
