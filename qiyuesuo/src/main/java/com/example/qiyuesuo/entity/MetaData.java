package com.example.qiyuesuo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {
    int id;
    double size;
    String type;
    String fileName;
    String originalName;
    LocalDateTime createTime;
    String address;

    public MetaData(double size, String type, String fileName, String originalName, String address) {
        this.size = size;
        this.type = type;
        this.fileName = fileName;
        this.originalName = originalName;
        this.address = address;
    }
}
