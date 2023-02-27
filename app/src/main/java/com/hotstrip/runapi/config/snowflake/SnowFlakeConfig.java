package com.hotstrip.runapi.config.snowflake;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花算法生成器
 * @author zhangxin
 * @since 2023-01-05
 */

@Configuration
public class SnowFlakeConfig {

    @Value("${snowflake.workerId:1L}")
    private long workerId;
    @Value("${snowflake.datacenterId:1L}")
    private long datacenterId;

    @Bean
    public SnowFlakeTemplate snowflakeTemplate() {
        return new SnowFlakeTemplate(workerId, datacenterId);
    }
}
