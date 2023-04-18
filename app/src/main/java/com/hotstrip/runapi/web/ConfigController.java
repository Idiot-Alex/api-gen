package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.model.Config;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.service.ConfigService;
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
    private ConfigService configService;

    /**
     * 从数据库中加载配置信息接口
     * @return
     */
    @PostMapping("/load")
    public R loadConfig() {
        String prefix = "client_";
        List<Config> configList = configService.listByName(prefix);
        return R.okData(configList);
    }
}
