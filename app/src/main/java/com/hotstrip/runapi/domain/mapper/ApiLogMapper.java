package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApiLogMapper extends BaseMapper<ApiLog> {
//    @Select({" select * FROM PUBLIC.api_log order by create_time desc"})
    Page<ApiLog> listPage(Page page, @Param("info") ApiLog info);
}
