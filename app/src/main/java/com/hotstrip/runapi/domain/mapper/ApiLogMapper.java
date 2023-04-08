package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.HostCount;
import com.hotstrip.runapi.domain.model.dto.SiteCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApiLogMapper extends BaseMapper<ApiLog> {

    // list page
    Page<ApiLog> listPage(Page page, @Param("info") ApiLog info);

    @Select("select host, count(*) as count from PUBLIC.api_log group by host")
    List<HostCount> listGroupByHost();

    @Select("select site, count(*) as count from PUBLIC.api_log group by site")
    List<SiteCount> listGroupBySite();
}
