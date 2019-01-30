package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.UserBean;
import com.bean.UserInfoBean;
import com.dao.UserMapper;
import com.entity.User;
import com.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CAIYUHUI
 * @create 2018/09/15 20:47
 **/
@Service("com.service.UserService")
public class UserService {
    @Resource(name = "com.dao.UserMapper")
    private UserMapper userMapper;
    @Autowired
    private UserInfoBean userInfoBean;

    /**
     * 根据指定条件查询用户表
     *
     * @param userVo 查询条件
     * @return 返回查询结果及记录分页的页数 对象UserBean
     */
    public UserBean findUser(UserVo userVo) {
        Page<User> page = new Page<>(); //分页
        if (userVo.getPageSize() > 0) {
            page.setSize(userVo.getPageSize());
        } else {
            page.setSize(5);
        }

        if (userVo.getPage() > 0) {
            page.setCurrent(userVo.getPage());
        } else {
            page.setCurrent(1);
        }
        /*page.setOrderByField("create_time");*/

        UserBean userBean = new UserBean();
        userBean.setUsers(userMapper.findUser(page, userVo));
        userBean.setPages(page.getPages());
        userBean.setPageSize(page.getSize());
        userBean.setIndex(page.getCurrent());
        userBean.setTotal(page.getTotal());

        return userBean;
    }

    /**
     * 登录逻辑
     *
     * @param userVo 用户账号，密码
     * @return 返回用户是否存在
     */
    public boolean login(UserVo userVo) {
        return userMapper.judgeUserByAccount(userVo) == 1;
    }

    /**
     * 添加新用户
     *
     * @param user 新用户信息
     * @throws Exception 操作异常
     */
    public void addUser(User user) throws Exception {
        user.setCreator(userInfoBean.getCurrentUser());
        user.setModifier(userInfoBean.getCurrentUser());
        userMapper.insert(user);
    }

    /**
     * 修改用户信息
     *
     * @param userVo 修改内容
     * @throws Exception 数据库操作异常
     */
    public void modifyUser(UserVo userVo) throws Exception {
        userVo.setModifier(userInfoBean.getCurrentUser());
        userMapper.updateUser(userVo);
    }

    /**
     * 逻辑删除一条或多条记录
     *
     * @param ids 要删除的记录的id值组成的数组
     * @throws Exception 　数据库操作异常
     */
    public void deleteUser(String[] ids) throws Exception {
        userMapper.deleteUserById(ids, userInfoBean.getCurrentUser());
    }
}
