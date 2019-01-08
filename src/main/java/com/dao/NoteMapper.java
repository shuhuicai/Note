package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.Note;
import com.vo.NoteVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2018/09/18 20:03
 **/
@Repository("com.dao.NoteMapper")
public interface NoteMapper extends BaseMapper<Note> {

    /**
     * 根据指定条件查询笔记内容
     *
     * @param page   分页查询
     * @param noteVo 查询条件（可选）id,creator,createTime,noteContent,folderId
     * @return 返回查询结果
     */
    List<Note> findNote(Pagination page, NoteVo noteVo);

    /**
     * 修改笔记内容
     *
     * @param noteVo 修改的内容
     * @return 返回修改成功与否
     */
    int updateNote(NoteVo noteVo);

    /**
     * 逻辑删除指定id值的笔记记录
     *
     * @param id 要删除的记录的id组成的数组
     * @return 返回删除成功与否
     */
    int deleteNote(String[] id);
}
