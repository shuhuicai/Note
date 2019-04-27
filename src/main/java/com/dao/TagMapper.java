package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.Tag;
import com.vo.TagVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签
 *
 * @author CAIYUHUI
 * @create 2018/09/18 09:40
 **/
@Repository("com.dao.TagMapper")
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 判断该"用户"是否存在指定标签内容的标签
     *
     * @param map
     * @return
     */
    int isExistTag(Map<String, String> map);

    /**
     * 获取标签id
     *
     * @param account
     * @param tagContent
     * @return
     */
    String getTagId(@Param("account") String account, @Param("tagContent") String tagContent);

    /**
     * 查询用户的所有标签
     *
     * @param account
     * @return
     */
    List<TagVo> getUserTag(String account);
}