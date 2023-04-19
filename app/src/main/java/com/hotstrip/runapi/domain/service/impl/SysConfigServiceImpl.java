package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.SysConfigMapper;
import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    public Page<SysConfig> listPage(Integer pageNo, Integer pageSize, SysConfig info) {
        return sysConfigMapper.listPage(new Page(pageNo, pageSize), info);
    }

    @Override
    public List<SysConfig> listByKey(String keyPrefix) {
        return lambdaQuery().like(SysConfig::getParamKey, keyPrefix).list();
    }
}
