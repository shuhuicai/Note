package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 标签实体类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 13:36
 **/
@TableName("t_tag")
public class Tag extends BaseEntity {
    private String tagContent; //标签内容

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }
}
