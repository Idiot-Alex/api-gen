package com.hotstrip.runapi;

import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@Slf4j
public class SysSysConfigServiceTest extends ApplicationTests{
    @Resource
    private SysConfigService sysConfigService;

    @Test
    public void testInsert() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setId(1L);
        sysConfig.setParamKey("test");
        sysConfig.setParamValue("1");
        boolean flag = sysConfigService.saveOrUpdate(sysConfig);
        log.info("insert config: {}", flag);
    }
}
