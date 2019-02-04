package com.controller;

import com.bean.UserBean;
import com.bean.UserInfoBean;
import com.entity.User;
import com.service.UserService;
import com.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author CAIYUHUI
 * @create 2018/10/14 15:48
 **/
@Controller("com.controller.UserController")
@RequestMapping("/user")
public class UserController {

    @Resource(name = "com.service.UserService")
    private UserService userService;
    //    @Resource(name = "userInfoBean")
    @Autowired
    private UserInfoBean userInfoBean;

    /**
     * 用户登录
     *
     * @param userVo 登录账号及密码
     * @return true or false 登录成功与否
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean doLogin(@RequestBody UserVo userVo, HttpServletRequest request) {
        if (userService.login(userVo)) {
            HttpSession session = request.getSession();//将用户、密码保存到Session中
            session.setAttribute("username", userVo.getAccount());
            session.setAttribute("password", userVo.getPassword());
            userInfoBean.setCurrentUser(userVo.getAccount());//将当前用户保存至UserInfoBean对象中
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询所有用户
     *
     * @param userVo 查询条件
     * @return 返回查询结果
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    @ResponseBody
    public UserBean queryUser(@RequestBody UserVo userVo) {
        return userService.findUser(userVo);
    }

    /**
     * 修改用户信息
     *
     * @param userVo 修改用户账号，密码
     * @return 返回所有有效的用户
     */
    @RequestMapping("/modifyUser")
    @ResponseBody
    public UserBean modifyUser(@RequestBody UserVo userVo, HttpServletRequest request) {
        try {
            if (!userService.modifyUser(userVo, request)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserVo u = new UserVo();
        u.setId(userVo.getId());
        return userService.findUser(u);
    }

    /**
     * 通过id值删除指定用户
     *
     * @param id 用户记录id
     * @return 返回所有有效的用户
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public UserBean deleteUser(String id) {
        String[] ids = new String[]{id};
        try {
            userService.deleteUser(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.findUser(new UserVo());
    }

    /**
     * 添加新的用户（角色目前只为普通用户1）
     *
     * @param user 用户信息
     * @return 返回所有有效的用户
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public UserBean addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.findUser(new UserVo());
    }
}