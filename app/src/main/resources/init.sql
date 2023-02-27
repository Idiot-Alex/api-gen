
create table if not exists `PUBLIC`.`api_log`(
    `id` bigint not null primary key ,
    `url` varchar(1024) not null,
    `method` varchar(10) null,
    `resource_type` varchar(20) not null,
    `request_headers` CLOB null,
    `post_data` CLOB null,
    `failed` varchar(10) null,
    `errorText` varchar(1024) null,
    `response_headers` CLOB null,
    `text` CLOB null,
    `status` int null,
    `statusText` varchar(1024) null
);