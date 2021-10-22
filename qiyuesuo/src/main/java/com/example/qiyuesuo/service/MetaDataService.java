package com.example.qiyuesuo.service;

import com.example.qiyuesuo.entity.MetaData;
import com.example.qiyuesuo.mapper.MetaDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataService {
    @Autowired
    MetaDataMapper mapper;

    public boolean addRecord(MetaData metaData){
        int count = mapper.addRecord(metaData);
        return count > 0;
    }

    public String findPathByFileName(String fileName) {
        return mapper.findPathByFileName(fileName);
    }

    public MetaData findMetaByFileName(String fileName) {
        return mapper.findMetaByFileName(fileName);
    }

}
