-- api log table
create table if not exists `PUBLIC`.`api_log`(
    `id` bigint not null primary key,
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

-- sysConfig table
create table if not exists `PUBLIC`.`sys_config`(
    `id` bigint not null primary key,
    `param_key` varchar(255) not null,
    `param_value` CLOB null,
    `param_type` varchar(255) null,
    `description` varchar(255) null,
    `create_time` timestamp null
);

-- api document table
create table if not exists `PUBLIC`.`api_doc`(
    `id` bigint not null primary key,
    `name` varchar(255) not null,
    `description` varchar(500) null,
    `api_count` int default 0,
    `create_time` timestamp null
);

-- api document log table
create table if not exists `PUBLIC`.`api_doc_log`(
    `id` bigint not null primary key,
    `doc_id` bigint not null,
    `api_id` bigint not null,
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