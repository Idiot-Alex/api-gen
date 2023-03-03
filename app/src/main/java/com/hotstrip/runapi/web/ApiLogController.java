package com.hotstrip.runapi.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * api log controller
 * @author hotstrip
 * @since 2023-03-03
 */
@Slf4j
@RestController
@RequestMapping("/api-log")
public class ApiLogController {

    @Resource
    private ApiLogService apiLogService;

    // api list
    @GetMapping("/list")
    public Page<ApiLog> list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                             ApiLog info) {
        Page<ApiLog> page = apiLogService.listPage(pageNo, pageSize, info);
        return page;
    }
}
