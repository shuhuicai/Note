package com.bean;

/**
 * 用于返回相关数据
 *
 * @author CAIYUHUI
 * @create 2019/01/23 22:27
 **/
public class ResultBean<T> {
    private T data;
    private int result;//0失败，1成功

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
