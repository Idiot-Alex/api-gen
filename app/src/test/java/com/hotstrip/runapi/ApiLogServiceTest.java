package com.hotstrip.runapi;

import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.service.ApiLogService;
import com.hotstrip.runapi.domain.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class ApiLogServiceTest extends ApplicationTests{
    @Resource
    private ApiLogService apiLogService;

    @Test
    public void testList() {
        List<ApiLog> list = apiLogService.list();
        log.info("list api log: {}", list);
    }
}
