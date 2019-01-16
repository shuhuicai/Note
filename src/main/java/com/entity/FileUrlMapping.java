package com.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 文件存放在磁盘上的路径与访问路径的映射
 *
 * @author CAIYUHUI
 * @create 2019/01/16 21:48
 **/
@TableName("t_file_url_mapping")
public class FileUrlMapping extends BaseEntity {
    private String diskUrl;//磁盘上的路径
    private String visitUrl;//访问路径

    public String getDiskUrl() {
        return diskUrl;
    }

    public void setDiskUrl(String diskUrl) {
        this.diskUrl = diskUrl;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    @Override
    public String toString() {
        return "FileUrlMapping{" +
                "diskUrl='" + diskUrl + '\'' +
                ", visitUrl='" + visitUrl + '\'' +
                '}';
    }
}
