package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dao.NoteContentMapper;
import com.entity.NoteContent;
import com.vo.NoteParamVo;
import com.vo.NoteVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2018/09/21 18:19
 **/
@Service("com.service.NoteContentService")
public class NoteContentService {

    @Resource(name = "com.dao.NoteContentMapper")
    private NoteContentMapper noteContentMapper;

    /**
     * 新增笔记(插入笔记的内容)
     *
     * @param note 笔记内容实体类（笔记内容，目录id）
     * @return 返回数据插入成功与否
     * @throws Exception
     */
    public boolean addNoteContent(NoteContent note) throws Exception {
        return noteContentMapper.insert(note) > 0;
    }

    /**
     * 查询笔记内容和名字
     *
     * @param id 笔记id值
     * @return
     */
    public NoteParamVo queryNoteInfo(String id) {
        List<NoteParamVo> res = noteContentMapper.queryNoteInfo(id);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return new NoteParamVo();
        }
    }


    /**
     * 根据指定条件查询笔记内容
     *
     * @param noteVo 查询条件
     * @return 返回查询结果
     */
    public List<NoteContent> findNote(NoteVo noteVo) {
        Page<NoteContent> page = new Page<>();
        page.setCurrent(noteVo.getPage());
        page.setSize(noteVo.getPageSize());

        return noteContentMapper.findNote(page, noteVo);
    }

    /**
     * 修改笔记内容
     *
     * @param noteVo 要修改的笔记内容
     * @throws Exception 数据库操作异常
     */
    public void updateNote(NoteVo noteVo) throws Exception {
        noteContentMapper.updateNote(noteVo);
    }

    /**
     * 逻辑删除笔记内容
     *
     * @param id 要删除的笔记的id组成的数组
     * @throws Exception 数据库操作异常
     */
    public void deleteNote(String[] id) throws Exception {
        noteContentMapper.deleteNote(id);
    }
}
