package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.mapstruct.ApiDocMapstruct;
import com.hotstrip.runapi.domain.model.ApiDoc;
import com.hotstrip.runapi.domain.model.R;
import com.hotstrip.runapi.domain.model.dto.ApiDocDto;
import com.hotstrip.runapi.domain.service.ApiDocLogService;
import com.hotstrip.runapi.domain.service.ApiDocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * api log controller
 * @author hotstrip
 * @since 2023-05-23
 */
@Slf4j
@RestController
@RequestMapping("/w/api-doc")
public class ApiDocController {
    @Resource
    private ApiDocLogService apiDocLogService;
    @Resource
    private ApiDocService apiDocService;
    @Resource
    private ApiDocMapstruct apiDocMapstruct;

    /**
     * create a new api doc
     * @param apiDocDto apiDocDto
     * @return
     */
    @PostMapping("/create")
    public R create(@RequestBody ApiDocDto apiDocDto) {
        // 检查参数
        Assert.notNull(apiDocDto, "apiDocDto is null");
        ApiDoc apiDoc = apiDocMapstruct.dtoToModel(apiDocDto);

        apiDocService.insert(apiDoc);
        apiDocLogService.copyApiLog(apiDoc.getId(), apiDocDto.getApiList());

        return R.okMsg("创建文档成功");
    }

}
