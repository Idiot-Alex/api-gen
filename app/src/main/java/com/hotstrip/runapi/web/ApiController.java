package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.enums.ResourceTypeEnum;
import com.hotstrip.runapi.domain.mapstruct.ApiLogMapstruct;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.model.SysConfig;
import com.hotstrip.runapi.domain.model.dto.ApiDto;
import com.hotstrip.runapi.domain.service.ApiLogService;
import com.hotstrip.runapi.domain.service.SysConfigService;
import com.hotstrip.runapi.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * api controller
 * @author hotstrip
 * @since 2023-03-03
 */
@Slf4j
@RestController
@RequestMapping("/w/api")
public class ApiController {

    @Resource
    private ApiLogService apiLogService;
    @Resource
    private ApiLogMapstruct apiLogMapstruct;
    @Resource
    private SysConfigService sysConfigService;

    @PostMapping("/upload")
    public R upload(@RequestBody ApiDto apiDto) {
        // 检查参数
        Assert.notNull(apiDto, "apiDto is null");
        Assert.notNull(apiDto.getRequest(), "param request is null");
        Assert.notNull(apiDto.getRequest().getResourceType(), "param request.resourceType is null");
        log.debug(apiLogMapstruct.dtoToModel(apiDto).toString());

        String resourceType = apiDto.getRequest().getResourceType();
        // 判断 resourceType 是否正常
        if (!ResourceTypeEnum.isExistType(resourceType)) {
            log.error("resourceType is not exist, resourceType: {}", resourceType);
            return R.error();
        }

        // 判断 resourceType 是否需要记录
        if (!checkResourceType(resourceType)) {
            log.warn("resourceType is not need to record, resourceType: {}", resourceType);
        } else {
            // 记录到数据库
            apiLogService.insert(apiLogMapstruct.dtoToModel(apiDto));
        }

        return R.ok();
    }

    private boolean checkResourceType(String resourceType) {
        boolean flag = false;
        // 检查配置信息中需要记录的 resourceType
        List<SysConfig> list = sysConfigService.listByKey("server_include_resource_type");
        for(SysConfig sysConfig : list) {
            // string
            if ("string".equals(sysConfig.getParamType())) {
                if (resourceType.equals(sysConfig.getParamValue())) {
                    flag = true;
                }
            }
            // array
            if ("array".equals(sysConfig.getParamType())) {
                List<String> resourceTypes = JacksonUtil.toArray(sysConfig.getParamValue(), String.class);
                if (resourceTypes.contains(resourceType)) {
                    flag = true;
                }
            }

            if (flag) {
                return flag;
            }
        }
        return flag;
    }

    // 判断程序是否运行的接口
    @PostMapping("/health")
    public R isRunning() {
        log.info("health check...ok");
        return R.ok();
    }

}
