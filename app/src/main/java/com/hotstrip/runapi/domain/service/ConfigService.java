package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.Config;

import java.util.List;

public interface ConfigService extends IService<Config> {

    Page<Config> listPage(Integer pageNo, Integer pageSize, Config info);

    List<Config> listByName(String namePrefix);
}
