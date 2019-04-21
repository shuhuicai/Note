package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.DataBean;
import com.dao.UserMapper;
import com.entity.User;
import com.util.Constant;
import com.vo.UserVo;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @author CAIYUHUI
 * @create 2018/09/15 20:47
 **/
@Service("com.service.UserService")
public class UserService {
    @Resource(name = "com.dao.UserMapper")
    private UserMapper userMapper;

    /**
     * 根据指定条件查询用户表
     *
     * @param userVo 查询条件
     * @return 返回查询结果及记录分页的页数 对象UserBean
     */
    public DataBean findUser(UserVo userVo) {
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

        DataBean<User> userBean = new DataBean<>();
        userBean.setLists(userMapper.findUser(page, userVo));
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
    public boolean addUser(User user, HttpServletRequest request) throws Exception {
        /*HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        user.setCreator(username);
        user.setModifier(username);*/
        if (isAccountRegister(user.getAccount())) {
            return false;
        } else {
            return userMapper.insert(user) > 0;
        }
    }

    /**
     * 判断账号是否注册过
     *
     * @param account 账号
     * @return true 注册过,false 未注册
     */
    private boolean isAccountRegister(String account) {
        return userMapper.isUserExist(account) > 0;
    }

    /**
     * 修改用户信息
     *
     * @param userVo 修改内容
     * @throws Exception 数据库操作异常
     */
    public boolean modifyUser(UserVo userVo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
//        userVo.setModifier(userInfoBean.getCurrentUser());
        userVo.setModifier((String) session.getAttribute("username"));
        return userMapper.updateUser(userVo) > 0;
    }

    /**
     * 逻辑删除一条或多条记录
     *
     * @param ids 要删除的记录的id值组成的数组
     * @throws Exception 　数据库操作异常
     */
    public boolean deleteUser(String[] ids, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        return userMapper.deleteUserById(ids, (String) session.getAttribute("username")) > 0;
    }

    /**
     * 发送验证码到指定邮箱
     *
     * @param address 目的邮箱地址
     * @return 验证码
     */
    public String sendVerifyCode(String address) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(Constant.host);
        mailSender.setUsername(Constant.email);
        mailSender.setPassword(Constant.password);

        Random random = new Random();
        StringBuffer verifyCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            verifyCode.append(random.nextInt(9) + "");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(address);
            helper.setFrom(Constant.email);
            helper.setSubject("注册验证码");//主题
            helper.setText("【在线笔记本】您的验证码:" + verifyCode + ",请于5分钟内正确输入,如非本人操作,请忽略此邮件");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return verifyCode.toString();
    }
}
