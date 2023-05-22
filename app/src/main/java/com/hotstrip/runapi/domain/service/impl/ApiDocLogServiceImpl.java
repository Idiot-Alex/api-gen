package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.config.snowflake.SnowFlakeTemplate;
import com.hotstrip.runapi.domain.mapper.ApiDocLogMapper;
import com.hotstrip.runapi.domain.model.ApiDocLog;
import com.hotstrip.runapi.domain.service.ApiDocLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiDocLogServiceImpl extends ServiceImpl<ApiDocLogMapper, ApiDocLog> implements ApiDocLogService {
    @Resource
    private ApiDocLogMapper apiDocLogMapper;
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
}
