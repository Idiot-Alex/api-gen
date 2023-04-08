package com.hotstrip.runapi.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.model.dto.HostCount;
import com.hotstrip.runapi.domain.model.dto.SiteCount;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/list")
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
    @PostMapping("/statistics")
    public R statics() {
        Map map = new HashMap();
        // 统计 api 总数
        long totalCount = apiLogService.count();
        // 统计 host 分组数
        List<HostCount> hostCountList = apiLogService.listGroupByHost();
        // 统计 site 分组数
        List<SiteCount> siteCountList = apiLogService.listGroupBySite();

        map.put("totalCount", totalCount);
        map.put("hostCount", hostCountList.size());
        map.put("siteCount", siteCountList.size());
        map.put("hostList", hostCountList);
        map.put("siteList", siteCountList);

        return R.ok(map);
    }
}
