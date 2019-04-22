package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.RandomId;

import java.util.Date;


/**
 * 基类
 *
 * @author CAIYUHUI
 * @create 2018/09/15 12:50
 **/
public class BaseEntity {
    private String id;  //表记录ID
    private String creator;  //创建人
    private Date createTime;  //创建时间
    private String modifier;  //修改人
    private Date modifyTime; // 修改时间
    private int isValid;  // 是否有效（0无效   1有效   2回收站）
    private String remarks;  //说明

    public BaseEntity() {
        id = RandomId.randGenerate();
        creator = "#";
        modifier = "#";
        createTime = new Date();
        modifyTime = new Date();
        isValid = 1;
    }

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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}