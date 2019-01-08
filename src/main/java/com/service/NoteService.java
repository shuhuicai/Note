package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dao.NoteMapper;
import com.entity.Note;
import com.vo.NoteVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2018/09/21 18:19
 **/
@Service("com.service.NoteService")
public class NoteService {

    @Resource
    private NoteMapper noteMapper;

    /**
     * 根据指定条件查询笔记内容
     *
     * @param noteVo 查询条件
     * @return 返回查询结果
     */
    public List<Note> findNote(NoteVo noteVo) {
        Page<Note> page = new Page<>();
        page.setCurrent(noteVo.getPage());
        page.setSize(noteVo.getPageSize());

        return noteMapper.findNote(page, noteVo);
    }

    /**
     * 新增笔记
     *
     * @param note 笔记内容
     * @throws Exception 数据库操作异常
     */
    public void addNote(Note note) throws Exception {
        noteMapper.insert(note);
    }

    /**
     * 修改笔记内容
     *
     * @param noteVo 要修改的笔记内容
     * @throws Exception 数据库操作异常
     */
    public void updateNote(NoteVo noteVo) throws Exception {
        noteMapper.updateNote(noteVo);
    }

    /**
     * 逻辑删除笔记内容
     *
     * @param id 要删除的笔记的id组成的数组
     * @throws Exception 数据库操作异常
     */
    public void deleteNote(String[] id) throws Exception {
        noteMapper.deleteNote(id);
    }
}
