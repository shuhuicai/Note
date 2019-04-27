package com.service;

import com.dao.TagMapper;
import com.entity.Tag;
import com.util.SessionUtil;
import com.vo.TagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAIYUHUI
 * @create 2018/09/18 10:31
 **/
@Service("com.service.TagService")
public class TagService {

    @Resource(name = "com.dao.TagMapper")
    private TagMapper tagMapper;

    /**
     * 插入新的标签
     *
     * @param tagContent 标签内容
     * @param request
     * @return
     */
    public boolean addTag(String tagContent, HttpServletRequest request) throws Exception {
        Tag tag = new Tag();
        tag.setTagContent(tagContent);
        tag.setCreator(SessionUtil.getCurrentUser(request));
        return tagMapper.insert(tag) > 0;
    }

    /**
     * 判断用户是否创建了该标签
     *
     * @param tagContent
     * @param account
     * @return true存在  false失败
     */
    public boolean isExistTag(String tagContent, String account) {
        Map<String, String> map = new HashMap<>();
        map.put("tagContent", tagContent);
        map.put("account", account);
        return tagMapper.isExistTag(map) > 0;
    }

    /**
     * 获取标签id值
     *
     * @param tagContent
     * @param account
     * @return
     */
    public String getTagId(String tagContent, String account) {
        return tagMapper.getTagId(account, tagContent);
    }

    /**
     * 获取指定用户的所有标签
     *
     * @param request
     * @return
     */
    public List<TagVo> getUserTags(HttpServletRequest request) {
        return tagMapper.getUserTag(SessionUtil.getCurrentUser(request));
    }
}
