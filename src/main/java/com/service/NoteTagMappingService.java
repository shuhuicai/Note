package com.service;

import com.dao.NoteTagMappingMapper;
import com.entity.NoteTagMapping;
import com.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAIYUHUI
 * @create 2019/04/25 23:03
 **/
@Service("com.service.NoteTagMappingService")
public class NoteTagMappingService {

    @Resource(name = "com.dao.NoteTagMappingMapper")
    private NoteTagMappingMapper noteTagMappingMapper;

    /**
     * 为笔记添加新的标签
     *
     * @param noteId tagId
     * @return
     */
    public boolean addTagToNote(String noteId, String tagId, HttpServletRequest request) throws Exception {
        NoteTagMapping noteTagMapping = new NoteTagMapping();
        noteTagMapping.setNoteId(noteId);
        noteTagMapping.setTagId(tagId);
        //插入前先检测笔记是否已添加该标签
        if (!isExistTag(noteId, tagId)) {
            //不存在该插入
            noteTagMapping.setCreator(SessionUtil.getCurrentUser(request));
            return noteTagMappingMapper.insert(noteTagMapping) > 0;
        }
        return false;
    }

    /**
     * 查询指定笔记的所有标签内容 返回标签的内容
     *
     * @param noteId
     * @return
     */
    public List<String> queryTagByNoteId(String noteId) {
        return noteTagMappingMapper.findAllTagByNoteId(noteId);
    }

    /**
     * 判断指定笔记是否有该标签
     *
     * @param noteId 笔记id
     * @param tagId  标签id
     * @return true 存在    false 不存在
     */
    private boolean isExistTag(String noteId, String tagId) {
        Map<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        map.put("tagId", tagId);
        return noteTagMappingMapper.isTagExist(map) > 0;
    }

    /**
     * 删除指定笔记的标签
     *
     * @param tagContent
     * @param noteId
     * @return
     */
    public boolean deleteTagInNote(String tagContent, String noteId, HttpServletRequest request) throws Exception {
        return noteTagMappingMapper.deleteTagInNote(tagContent, noteId, SessionUtil.getCurrentUser(request)) > 0;
    }
}
