package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.ConfigMapper;
import com.hotstrip.runapi.domain.model.Config;
import com.hotstrip.runapi.domain.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    public Page<Config> listPage(Integer pageNo, Integer pageSize, Config info) {
        return configMapper.listPage(new Page(pageNo, pageSize), info);
    }

    @Override
    public List<Config> listByName(String namePrefix) {
        return lambdaQuery().like(Config::getName, namePrefix).list();
    }
}
