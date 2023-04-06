package com.hotstrip.runapi.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public R list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                             ApiLog info) {
        log.info("get api log list...pageNo: {}, pageSize: {}, info: {}", pageNo, pageSize, info);
        Page<ApiLog> page = apiLogService.listPage(pageNo, pageSize, info);
        return R.ok(page.getRecords())
                .put("total", page.getTotal());
    }

    /**
     * delete by id
     * @param id id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        log.info("delete api log by id...id: {}", id);
        apiLogService.removeById(id);
        return R.ok();
    }

    /**
     * 统计信息
     * @return
     */
    public R statics() {
        // 统计 api 总数
        long totalCount = apiLogService.count();
        // 统计 api 分组数
        // apiLogService.groupsCount();
        return R.ok();
    }
}
