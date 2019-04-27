package com.controller;

import com.service.NoteTagMappingService;
import com.service.TagService;
import com.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2019/04/26 21:56
 **/
@Controller
@RequestMapping("/tag")
public class TagController {

    @Resource(name = "com.service.TagService")
    private TagService tagService;

    @Resource(name = "com.service.NoteTagMappingService")
    private NoteTagMappingService noteTagMappingService;

    /**
     * 为笔记添加新的标签
     *
     * @param noteId     笔记id
     * @param tagContent 标签内容
     * @return true or false
     */
    @RequestMapping(value = "/addTagToNote", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTagToNote(String noteId, String tagContent, HttpServletRequest request) {
        String account = SessionUtil.getCurrentUser(request);
        //先判断该用户是否创建过该标签
        //如果没有则将该标签插入表中
        try {
            if (!tagService.isExistTag(tagContent, account)) {
                tagService.addTag(tagContent, request);
            }
            //获取标签id值
            String tagId = tagService.getTagId(tagContent, account);
            //再将该标签添加到笔记
            return noteTagMappingService.addTagToNote(noteId, tagId, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询笔记的所有标签
     *
     * @param noteId
     * @return
     */
    @RequestMapping(value = "/getAllTag", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getAllTag(String noteId) {
        return noteTagMappingService.queryTagByNoteId(noteId);
    }

    /**
     * 删除笔记的标签
     *
     * @param noteId
     * @param tagContent
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteTagInNote", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteTagInNote(String noteId, String tagContent, HttpServletRequest request) {
        try {
            return noteTagMappingService.deleteTagInNote(tagContent, noteId, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
