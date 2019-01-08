package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 用户实体类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 13:29
 **/
@TableName("t_user")
public class User extends BaseEntity {
    private String account; //账号
    private String password; //密码
    private int role;  //角色（0管理员  1普通用户）

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}