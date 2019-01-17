package com.service;

import com.dao.FileUrlMappingMapper;
import com.entity.FileUrlMapping;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 文件url映射Service类
 *
 * @author CAIYUHUI
 * @create 2019/01/16 22:12
 **/
@Service("com.service.FileUrlMappingService")
public class FileUrlMappingService {

    @Resource(name = "com.dao.FileUrlMappingMapper")
    private FileUrlMappingMapper mappingMapper;

    /**
     * 根据url查询出文件存储的路径
     *
     * @param visitUrl 参数 url
     * @return 返回文件在磁盘上的路径
     */
    public String queryUrl(String visitUrl) {
        return mappingMapper.findDiskUrlByVisitUrl(visitUrl);
    }

    /**
     * 增加新的文件映射记录
     *
     * @param mapping 给外界访问的url  文件存放在磁盘的路径
     * @return 返回添加成功与否
     */
    public boolean addUrl(FileUrlMapping mapping) throws Exception {
        return mappingMapper.insert(mapping) > 0;
    }
}
