package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 笔记内容实体类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 13:33
 **/
@TableName("t_note_content")
public class NoteContent extends BaseEntity {
    private String fileId; //该笔记目录名Id
    private String noteContent;//笔记内容

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
