package com.hotstrip.runapi.web;

import com.hotstrip.runapi.domain.mapstruct.ApiLogMapstruct;
import com.hotstrip.runapi.domain.model.dto.ApiDto;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiLogService apiLogService;
    @Resource
    private ApiLogMapstruct apiLogMapstruct;

    @PostMapping("/upload")
    public String upload(@RequestBody ApiDto apiDto) {
        log.info(apiLogMapstruct.dtoToModel(apiDto).toString());
        apiLogService.insert(apiLogMapstruct.dtoToModel(apiDto));
        return "ok";
    }

}
