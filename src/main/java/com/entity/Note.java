package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 笔记实体类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 13:33
 **/
@TableName("t_note")
public class Note extends BaseEntity {
    private String noteContent;//笔记内容
    private String folderId; //该笔记归属的文件夹Id

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
}
