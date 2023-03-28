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
@RequestMapping("/w/api-log")
public class ApiLogController {

    @Resource
    private ApiLogService apiLogService;

    /**
     * api list
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param info ApiLog
     * @return Page<ApiLog>
     */
    @GetMapping("/list")
    public Page<ApiLog> list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                             ApiLog info) {
        log.info("ApiLogList...pageNo: {}, pageSize: {}, info: {}", pageNo, pageSize, info);
        Page<ApiLog> page = apiLogService.listPage(pageNo, pageSize, info);
        return page;
    }

    /**
     * 统计信息
     * @return
     */
    public Object statics() {
        // 统计 api 总数
        apiLogService.count();
        // 统计 api 分组数
//        apiLogService.groupsCount();
        return null;
    }
}
