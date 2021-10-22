package com.example.qiyuesuo.mapper;

import com.example.qiyuesuo.entity.MetaData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaDataMapper {

    //上传文件，数据库新增记录
    int addRecord(MetaData metaData);
    //下载文件，根据文件名获得本地路径
    String findPathByFileName(String fileName);
    //根据文件名获得所有数据
    MetaData findMetaByFileName(String fileName);

}
