package com.vo;

/**
 * 创建笔记，接收前端的参数类
 *
 * @author CAIYUHUI
 * @create 2019/03/03 21:27
 **/
public class NoteParamVo {
    private String parentId;
    private String content;
    private String label;//笔记名
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
