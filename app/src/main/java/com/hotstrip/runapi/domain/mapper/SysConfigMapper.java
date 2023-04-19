package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    Page<SysConfig> listPage(Page page, @Param("info") SysConfig info);
}
