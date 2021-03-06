package com.controller;

import com.bean.DataBean;
import com.entity.User;
import com.service.UserService;
import com.util.SessionUtil;
import com.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author CAIYUHUI
 * @create 2018/10/14 15:48
 **/
@Controller("com.controller.UserController")
@RequestMapping("/user")
public class UserController {

    @Resource(name = "com.service.UserService")
    private UserService userService;

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
            //将当前用户保存至UserInfoBean对象中
            SessionUtil.getSession(request).setAttribute("username", userVo.getAccount());
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
    public DataBean queryUser(@RequestBody UserVo userVo) {
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
    public DataBean modifyUser(@RequestBody UserVo userVo, HttpServletRequest request) {
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
    public boolean deleteUser(String id, HttpServletRequest request) {
        String[] ids = new String[]{id};
        try {
            return userService.deleteUser(ids, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加新的用户（角色目前只为普通用户1）
     * 注册
     *
     * @param user 用户信息
     * @return 返回所有有效的用户
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(@RequestBody User user, HttpServletRequest request) {
        try {
            return userService.addUser(user, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送验证码
     *
     * @param address 发送的目的邮箱地址
     * @return 验证码
     */
    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public String sendVerifyCode(String address) {
        return userService.sendVerifyCode(address);
    }
}