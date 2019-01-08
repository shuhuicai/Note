package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.User;
import com.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CAIYUHUI
 * @create 2018/09/15 13:59
 **/
@Repository("com.dao.UserMapper")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户表
     *
     * @param page   分页
     * @param userVo 查询条件
     * @return 查询结果集
     */
    List<User> findUser(Pagination page, UserVo userVo);

    /**
     * 修改用户信息
     *
     * @param userVo 要修改的内容
     * @return 修改成功与否
     */
    int updateUser(UserVo userVo);

    /**
     * 逻辑删除指定id值的记录
     *
     * @param ids 要删除的记录的id组成的数据
     * @return 修改成功与否
     */
    int deleteUserById(String[] ids);
}
