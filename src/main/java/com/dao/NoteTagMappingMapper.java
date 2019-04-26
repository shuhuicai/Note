package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.NoteTagMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 笔记与标签映射
 *
 * @author CAIYUHUI
 * @create 2019/04/25 22:58
 **/
@Repository("com.dao.NoteTagMappingMapper")
public interface NoteTagMappingMapper extends BaseMapper<NoteTagMapping> {
    /**
     * 查询指定笔记的所有标签
     *
     * @param noteId 笔记id
     * @return
     */
    List<String> findAllTagByNoteId(String noteId);

    /**
     * 查询指定"笔记"是否存在该标签
     *
     * @param map
     * @return
     */
    int isTagExist(Map<String, String> map);
}
