package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.enums.ResourceTypeEnum;
import com.hotstrip.runapi.domain.mapstruct.ApiLogMapstruct;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.model.dto.ApiDto;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * api controller
 * @author hotstrip
 * @since 2023-03-03
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiLogService apiLogService;
    @Resource
    private ApiLogMapstruct apiLogMapstruct;

    @PostMapping("/upload")
    public R upload(@RequestBody ApiDto apiDto) {
        // 检查参数
        Assert.notNull(apiDto, "apiDto is null");
        Assert.notNull(apiDto.getRequest(), "param request is null");
        Assert.notNull(apiDto.getRequest().getResourceType(), "param request.resourceType is null");
        log.info(apiLogMapstruct.dtoToModel(apiDto).toString());

        String resourceType = apiDto.getRequest().getResourceType();
        // 判断 resourceType 是否需要记录
        if (!ResourceTypeEnum.isExistType(resourceType)) {
            log.error("resourceType is not exist, resourceType: {}", resourceType);
            return R.error();
        }

        // 检查配置信息中需要记录的 resourceType
        // TODO

        // 记录到数据库
        apiLogService.insert(apiLogMapstruct.dtoToModel(apiDto));
        return R.ok();
    }

    // 判断程序是否运行的接口
    @PostMapping("/health")
    public R isRunning() {
        return R.ok();
    }

    // 从数据库中加载配置信息接口
    @PostMapping("/config")
    public R loadConfig() {
        // TODO
        return R.ok();
    }

}
