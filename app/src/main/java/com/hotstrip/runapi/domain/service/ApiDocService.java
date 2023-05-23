package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.ApiDoc;

public interface ApiDocService extends IService<ApiDoc> {

    void insert(ApiDoc apiDoc);
}
