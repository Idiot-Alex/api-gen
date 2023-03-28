package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApiLogMapper extends BaseMapper<ApiLog> {

    // list page
    Page<ApiLog> listPage(Page page, @Param("info") ApiLog info);

    // group by url method
    @Select("select MAX(id) id, url, method as count from api_log group by url, method")
    List<ApiLog> listGroupByUrlMethod();
}
