package com.service;

import com.dao.NoteContentMapper;
import com.entity.NoteContent;
import com.util.SessionUtil;
import com.vo.NoteParamVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean addNoteContent(NoteContent note, HttpServletRequest request) throws Exception {
        note.setModifier(SessionUtil.getCurrentUser(request));
        note.setCreator(SessionUtil.getCurrentUser(request));
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
     * 修改笔记内容
     *
     * @param noteParamVo content id
     * @return true or false
     */
    public boolean updateNote(NoteParamVo noteParamVo, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("content", noteParamVo.getContent());
        map.put("id", noteParamVo.getId());
        map.put("modifier", SessionUtil.getCurrentUser(request));
        return noteContentMapper.updateNote(map) > 0;
    }
}
