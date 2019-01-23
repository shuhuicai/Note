package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

/**
 * 记得前端文件夹树的实体类
 *
 * @author CAIYUHUI
 * @create 2019/01/05 14:45
 **/
@TableName("t_folder_tree")
public class FolderTree extends BaseEntity {
    private String label;//文件夹名
    private String parentId;//父目录的id值（第一级目录时，该值为0）
    private int isFolder;//判断该记录是否是文件夹(0否，1是)
    private String fileUrl;//若为文件，则记录保存该文件的url路径
    private int fileType;//文件类型（图片0，pdf 1,word 2）

    @TableField(exist = false)
    private List<FolderTree> children;//记录该文件夹的所有子目录  非数据库字段

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(int isFolder) {
        this.isFolder = isFolder;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<FolderTree> getChildren() {
        return children;
    }

    public void setChildren(List<FolderTree> children) {
        this.children = children;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FolderTree{" +
                "label='" + label + '\'' +
                ", parentId='" + parentId + '\'' +
                ", isFolder=" + isFolder +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType=" + fileType +
                ", children=" + children +
                '}';
    }
}
