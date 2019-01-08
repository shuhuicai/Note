package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.Tag;
import com.vo.TagVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签
 *
 * @author CAIYUHUI
 * @create 2018/09/18 09:40
 **/
@Repository("com.dao.TagMapper")
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据指定条件查询标签
     *
     * @param page  分页查询
     * @param tagVo 查询条件对象
     * @return 返回查询结果
     */
    List<Tag> findTag(Pagination page, TagVo tagVo);


    /**
     * 修改标签内容
     *
     * @param tagVo 修改的内容
     * @return 返回修改成功与否
     */
    int updateTag(TagVo tagVo);

    /**
     * 逻辑删除标签
     *
     * @param ids 要删除的标签的id值组成的数组
     * @return 返回操作成功与否
     */
    int deleteTag(String[] ids);
}