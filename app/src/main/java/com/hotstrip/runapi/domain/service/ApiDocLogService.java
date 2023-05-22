package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.ApiDocLog;

import java.util.List;

public interface ApiDocLogService extends IService<ApiDocLog> {

    void insert(ApiDocLog apiLog);

    List<ApiDocLog> listBySite(String site);

    List<ApiDocLog> listByHost(String host);
}
