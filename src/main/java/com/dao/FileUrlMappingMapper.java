package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.FileUrlMapping;
import org.springframework.stereotype.Repository;

/**
 * FileUrlMappingMapper
 *
 * @author CAIYUHUI
 * @create 2019/01/16 21:53
 **/
@Repository("com.dao.FileUrlMappingMapper")
public interface FileUrlMappingMapper extends BaseMapper<FileUrlMapping> {

    /**
     * 根据url查询出文件存储的路径
     *
     * @param visitUrl 参数 url
     * @return 返回文件在磁盘上的路径
     */
    String findDiskUrlByVisitUrl(String visitUrl);
}
