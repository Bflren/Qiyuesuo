create table metadata.meta
(
    id            int auto_increment
        primary key,
    size          double                             null comment '文件大小',
    type          varchar(50)                        null comment '文件类型',
    file_name     varchar(50)                        null comment '文件名',
    original_name varchar(50)                        null comment '原始文件名',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    address       varchar(50)                        null comment '文件保存地址'
)
    comment '上传文件元数据';


