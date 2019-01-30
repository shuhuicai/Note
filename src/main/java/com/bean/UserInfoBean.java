package com.bean;

import org.springframework.stereotype.Component;

/**
 * 用户信息bean
 * 用于保存当前会话的用户信息
 *
 * @author CAIYUHUI
 * @create 2019/01/30 15:43
 **/
@Component
public class UserInfoBean {
    private String currentUser;//当前用户

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
