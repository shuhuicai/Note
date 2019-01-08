package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dao.TagMapper;
import com.entity.Tag;
import com.vo.TagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2018/09/18 10:31
 **/
@Service("com.service.TagService")
public class TagService {

    @Resource(name = "com.dao.TagMapper")
    private TagMapper tagMapper;

    /**
     * 查询标签
     * 可通过id值、创建人creator、创建时间createTime、标签内容tagContent
     * 其中一个或多个作为条件进行查询
     *
     * @param tagVo 查询条件
     * @return 返回查询结果
     */
    public List<Tag> findTag(TagVo tagVo) {
        Page<Tag> page = new Page<>();
        page.setCurrent(tagVo.getPage());
        page.setSize(tagVo.getPageSize());

        List<Tag> res = tagMapper.findTag(page, tagVo);
        return res;
    }

    /**
     * 添加新的标签
     * 只需给定要添加的标签的内容tagContent即可
     *
     * @param tag 标签信息
     * @throws Exception 操作异常
     */
    public void addTag(Tag tag) throws Exception {
        tagMapper.insert(tag);
    }

    /**
     * 修改指定id值的标签内容
     * 可修改标签的内容tagContent，必须指定要修改内容的id值
     *
     * @param tagVo 修改内容
     * @throws Exception 操作异常
     */
    public void modifyTag(TagVo tagVo) throws Exception {
        tagMapper.updateTag(tagVo);
    }

    /**
     * 逻辑删除指定id值的所有标签
     *
     * @param ids 要删除的标签id值组成的数组
     * @throws Exception 操作异常
     */
    public void deleteTag(String[] ids) throws Exception {
        tagMapper.deleteTag(ids);
    }
}
