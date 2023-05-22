package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotstrip.runapi.domain.model.ApiDocLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApiDocLogMapper extends BaseMapper<ApiDocLog> {

    @Select("select * from PUBLIC.api_doc_log where site = #{site}")
    List<ApiDocLog> listBySite(@Param("site") String site);

    @Select("select * from PUBLIC.api_doc_log where host = #{host}")
    List<ApiDocLog> listByHost(@Param("host") String host);
}
