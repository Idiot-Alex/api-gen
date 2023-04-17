-- api log table
create table if not exists `PUBLIC`.`api_log`(
    `id` bigint not null primary key ,
    `url` CLOB not null,
    `host` varchar(255) null,
    `site` varchar(255) null,
    `method` varchar(10) null,
    `resource_type` varchar(255) not null,
    `request_headers` CLOB null,
    `post_data` CLOB null,
    `failed` varchar(10) null,
    `error_text` varchar(1024) null,
    `request_body_size` int null,
    `request_headers_size` int null,
    `response_headers` CLOB null,
    `text` CLOB null,
    `status` int null,
    `status_text` varchar(1024) null,
    `response_body_size` int null,
    `response_headers_size` int null,
    `create_time` timestamp null
);

-- config table
create table if not exists `PUBLIC`.`config`(
    `id` bigint not null primary key ,
    `name` varchar(255) not null,
    `value` varchar(255) null,
    `description` varchar(255) null,
    `create_time` timestamp null
);