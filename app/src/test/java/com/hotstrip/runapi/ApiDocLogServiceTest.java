package com.hotstrip.runapi;

import com.hotstrip.runapi.domain.mapstruct.ApiDocLogMapstruct;
import com.hotstrip.runapi.domain.model.ApiDocLog;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.service.ApiDocLogService;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ApiDocLogServiceTest extends ApplicationTests{
    @Resource
    private ApiLogService apiLogService;
    @Resource
    private ApiDocLogService apiDocLogService;
    @Resource
    private ApiDocLogMapstruct apiDocLogMapstruct;

    @Test
    public void testList() {
        List<ApiDocLog> docLogList = new ArrayList<>();
        List<ApiLog> list = apiLogService.listByHost("unpkg.com");
        list.forEach(apiLog -> {
            log.info("api log url: {}", apiLog.getUrl());
            docLogList.add(apiDocLogMapstruct.apiLogToModel(apiLog));
        });

        log.info("doc log list size: {}", docLogList.size());
        docLogList.forEach(apiDocLog -> {
            log.info("api doc log url: {}", apiDocLog.getUrl());
        });
    }
}
