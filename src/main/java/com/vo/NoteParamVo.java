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
    private String noteName;

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

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
}
