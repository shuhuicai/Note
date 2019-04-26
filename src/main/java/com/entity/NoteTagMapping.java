package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 笔记－标签映射实体类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 13:38
 **/
@TableName("t_note_tag")
public class NoteTagMapping extends BaseEntity {
    private String noteId; //笔记Id
    private String tagId;  //标签Id

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
