package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * config controller
 * @author Hotstrip
 * @since 2023-04-18
 */
@Slf4j
@RestController
@RequestMapping("/w/config")
public class ConfigController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 从数据库中加载配置信息接口
     * @return
     */
    @PostMapping("/load")
    public R loadConfig() {
        String prefix = "client_";
        List<SysConfig> sysConfigList = sysConfigService.listByKey(prefix);
        return R.okData(sysConfigList);
    }
}
