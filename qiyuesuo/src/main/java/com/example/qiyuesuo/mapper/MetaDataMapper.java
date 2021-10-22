package com.example.qiyuesuo.mapper;

import com.example.qiyuesuo.entity.MetaData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaDataMapper {

    int addRecord(MetaData metaData);
    String findPathByFileName(String fileName);
    MetaData findMetaByFileName(String fileName);

}
