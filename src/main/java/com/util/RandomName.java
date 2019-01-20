package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成文件名工具类
 * 采用文件名 + 当前时间 + 随机数
 *
 * @author CAIYUHUI
 * @create 2019/01/19 23:54
 **/
public class RandomName {
    private static volatile Long now;
    private static volatile Long random;

    public synchronized static String getName(String name) {
        now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        random = (long) (Math.random() * now);
        String fileName = now + "" + random;
        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }
        return fileName;
    }
}
