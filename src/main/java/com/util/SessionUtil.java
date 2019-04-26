package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 获取当前会话的用户信息
 *
 * @author CAIYUHUI
 * @create 2019/04/23 20:01
 **/
public class SessionUtil {

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public static String getCurrentUser(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("username");
    }
}
