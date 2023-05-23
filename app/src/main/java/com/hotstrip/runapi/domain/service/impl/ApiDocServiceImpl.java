package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.config.snowflake.SnowFlakeTemplate;
import com.hotstrip.runapi.domain.mapper.ApiDocMapper;
import com.hotstrip.runapi.domain.model.ApiDoc;
import com.hotstrip.runapi.domain.service.ApiDocService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiDocServiceImpl extends ServiceImpl<ApiDocMapper, ApiDoc> implements ApiDocService {

    @Resource
    private SnowFlakeTemplate snowFlakeTemplate;

    @Override
    public void insert(ApiDoc apiDoc) {
        if (null == apiDoc.getId()) {
            apiDoc.setId(snowFlakeTemplate.getIdLong());
        }
        this.save(apiDoc);
    }
}
