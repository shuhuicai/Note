package com.bean;

import java.util.List;

/**
 * 返回结果的Bean 用于返回给客户端的数据
 * 保存查询结果及记录相应的页数
 *
 * @author CAIYUHUI
 * @create 2018/12/11 22:33
 **/
public class DataBean<T> {
    private List<T> lists; //查询结果
    private long pages; //页数
    private long total;//总记录数
    private int pageSize;//一页有多少条记录
    private int index;//当前第几页

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
