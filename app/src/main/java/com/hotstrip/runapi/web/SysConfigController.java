package com.hotstrip.runapi.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.config.snowflake.SnowFlakeTemplate;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * config controller
 * @author Hotstrip
 * @since 2023-04-18
 */
@Slf4j
@RestController
@RequestMapping("/w/config")
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;
    @Resource
    private SnowFlakeTemplate snowFlakeTemplate;

    /**
     * 从数据库中加载配置信息接口
     * @return
     */
    @PostMapping("/load-client")
    public R loadClientConfig() {
        String prefix = "client_";
        List<SysConfig> sysConfigList = sysConfigService.listByKey(prefix);
        return R.okData(sysConfigList);
    }

    /**
     * sys config list
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param info SysConfig
     * @return Page<SycConfig>
     */
    @PostMapping("/list")
    public R list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                  SysConfig info) {
        log.info("get sys config list...pageNo: {}, pageSize: {}, info: {}", pageNo, pageSize, info);
        Page<SysConfig> page = sysConfigService.listPage(pageNo, pageSize, info);
        return R.okData(page.getRecords())
                .put("total", page.getTotal());
    }

    /**
     * save sys config
     * @param info SysConfig
     * @return
     */
    @PostMapping("/save")
    public R save(SysConfig info) {
        log.info("save sys config...info: {}", info);
        Assert.notNull(info.getParamKey(), "paramKey must not be null");
        Assert.notNull(info.getParamValue(), "paramValue must not be null");
        Assert.notNull(info.getParamType(), "paramType must not be null");

        if (info.getId() == null) {
            info.setId(snowFlakeTemplate.getIdLong());
            info.setCreateTime(new Date());
        }
        boolean flag = sysConfigService.saveOrUpdate(info);
        if (!flag) {
            return R.errorMsg("save sys config error");
        }
        return R.ok();
    }

    /**
     * delete by id
     * @param id id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        log.info("delete sys config by id...id: {}", id);
        sysConfigService.removeById(id);
        return R.ok();
    }
}
