package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.config.snowflake.SnowFlakeTemplate;
import com.hotstrip.runapi.domain.mapper.ApiLogMapper;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.HostCount;
import com.hotstrip.runapi.domain.model.dto.SiteCount;
import com.hotstrip.runapi.domain.service.ApiLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements ApiLogService {
    @Resource
    private ApiLogMapper apiLogMapper;
    @Resource
    private SnowFlakeTemplate snowFlakeTemplate;

    @Override
    public Page<ApiLog> listPage(Integer pageNo, Integer pageSize, ApiLog info) {
        return apiLogMapper.listPage(new Page(pageNo, pageSize), info);
    }

    @Override
    public void insert(ApiLog apiLog) {
        if (null == apiLog.getId()) {
            apiLog.setId(snowFlakeTemplate.getIdLong());
        }
        this.save(apiLog);
    }

    @Override
    public List<HostCount> listGroupByHost() {
        return apiLogMapper.listGroupByHost();
    }

    @Override
    public List<SiteCount> listGroupBySite() {
        return apiLogMapper.listGroupBySite();
    }

    @Override
    public List<ApiLog> listBySite(String site) {
        return apiLogMapper.listBySite(site);
    }

    @Override
    public List<ApiLog> listByHost(String host) {
        return apiLogMapper.listByHost(host);
    }
}
