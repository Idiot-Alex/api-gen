package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.SysConfig;

import java.util.List;

public interface SysConfigService extends IService<SysConfig> {

    Page<SysConfig> listPage(Integer pageNo, Integer pageSize, SysConfig info);

    List<SysConfig> listByKey(String keyPrefix);
}
