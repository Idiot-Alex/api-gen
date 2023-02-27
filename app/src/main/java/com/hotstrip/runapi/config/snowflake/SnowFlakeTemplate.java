package com.hotstrip.runapi.config.snowflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Builder;
import lombok.Data;

/**
 * 雪花算法模版
 * @author zhangxin
 * @since 2023-01-05
 */
public class SnowFlakeTemplate {

    private Snowflake snowflake;

    public SnowFlakeTemplate(Long workerId, Long dataCenterId) {
        this.snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
    }


    public String getIdStr() {
        return snowflake.nextIdStr();
    }

    public long getIdLong() {
        return snowflake.nextId();
    }

}
