package com.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户Vo
 *
 * @author CAIYUHUI
 * @create 2018/09/15 17:09
 **/
public class UserVo {
    private String id;  //表记录ID
    private String creator;  //创建人
    private String createTime1;  //查询创建时间前
    private String createTime2; //查询创建时间后
    private String modifier; //修改人
    private Date modifyTime; //修改时间
    private String account; //账号
    private String password; //密码
    private int role;  //角色（0管理员  1普通用户）
    private String remarks; //说明
    private int page; //页面下标值
    private int pageSize; //页面大小

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(String createTime2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (!createTime2.isEmpty()) {
            try {
                calendar.setTime(sdf.parse(createTime2));
                calendar.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
                createTime2 = sdf.format(calendar.getTime());//再转换为String类型
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.createTime2 = createTime2;
        }else {
            this.createTime2="";
        }
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime1=" + createTime1 +
                ", createTime2=" + createTime2 +
                ", modifier='" + modifier + '\'' +
                ", modifyTime=" + modifyTime +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", remarks='" + remarks + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
