package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.config.snowflake.SnowFlakeTemplate;
import com.hotstrip.runapi.domain.mapper.ApiDocLogMapper;
import com.hotstrip.runapi.domain.mapstruct.ApiDocLogMapstruct;
import com.hotstrip.runapi.domain.model.ApiDocLog;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.service.ApiDocLogService;
import com.hotstrip.runapi.domain.service.ApiLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiDocLogServiceImpl extends ServiceImpl<ApiDocLogMapper, ApiDocLog> implements ApiDocLogService {
    @Resource
    private ApiDocLogMapper apiDocLogMapper;
    @Resource
    private ApiLogService apiLogService;
    @Resource
    private ApiDocLogMapstruct apiDocLogMapstruct;
    @Resource
    private SnowFlakeTemplate snowFlakeTemplate;

    @Override
    public void insert(ApiDocLog apiDocLog) {
        if (null == apiDocLog.getId()) {
            apiDocLog.setId(snowFlakeTemplate.getIdLong());
        }
        this.save(apiDocLog);
    }

    @Override
    public List<ApiDocLog> listBySite(String site) {
        return apiDocLogMapper.listBySite(site);
    }

    @Override
    public List<ApiDocLog> listByHost(String host) {
        return apiDocLogMapper.listByHost(host);
    }

    @Override
    public void copyApiLog(Long docId, List<String> apiList) {
        // 1. 查询出所有的 api log
        List<ApiLog> apiDocLogs = apiLogService.listByIds(apiList);
        // 2. 循环插入到 api doc log
        apiDocLogs.forEach(apiLog -> {
            ApiDocLog apiDocLog = apiDocLogMapstruct.apiLogToModel(apiLog);
            apiDocLog.setDocId(docId);
            this.insert(apiDocLog);
        });
    }
}
