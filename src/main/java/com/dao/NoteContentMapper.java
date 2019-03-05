package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.NoteContent;
import com.vo.NoteParamVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CAIYUHUI
 * @create 2018/09/18 20:03
 **/
@Repository("com.dao.NoteContentMapper")
public interface NoteContentMapper extends BaseMapper<NoteContent> {

    /**
     * 连表查询笔记名和内容
     *
     * @param id 笔记id
     * @return
     */
    List<NoteParamVo> queryNoteInfo(String id);

    /**
     * 修改笔记内容
     *
     * @param map
     * @return
     */
    int updateNote(Map<String, String> map);
}
