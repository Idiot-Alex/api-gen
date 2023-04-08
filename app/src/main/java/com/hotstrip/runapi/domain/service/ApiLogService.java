package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.HostCount;
import com.hotstrip.runapi.domain.model.dto.SiteCount;

import java.util.List;

public interface ApiLogService extends IService<ApiLog> {
    Page<ApiLog> listPage(Integer pageNo, Integer pageSize, ApiLog info);

    void insert(ApiLog apiLog);

    List<HostCount> listGroupByHost();

    List<SiteCount> listGroupBySite();
}
