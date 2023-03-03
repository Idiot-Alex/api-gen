package com.hotstrip.runapi.domain.mapstruct;

import cn.hutool.core.util.IdUtil;
import com.hotstrip.runapi.JacksonUtil;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * use spring control
 * @author hotstrip
 * @since 2022-12-31
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class, JacksonUtil.class})
public interface ApiLogMapstruct {

    @Mapping(target = "id", expression = "java(IdUtil.getSnowflake().nextId())")
    @Mapping(source = "request.resourceType", target = "resourceType")
    @Mapping(target = "requestHeaders", expression = "java(JacksonUtil.toJsonString(apiDto.getRequest().getHeaders()))")
    @Mapping(source = "request.postData", target = "postData")
    @Mapping(source = "request.failed", target = "failed")
    @Mapping(source = "request.errorText", target = "errorText")
    @Mapping(source = "request.requestBodySize", target = "requestBodySize")
    @Mapping(source = "request.requestHeadersSize", target = "requestHeadersSize")
    @Mapping(target = "responseHeaders", expression = "java(JacksonUtil.toJsonString(apiDto.getRequest().getHeaders()))")
    @Mapping(source = "response.text", target = "text")
    @Mapping(source = "response.status", target = "status")
    @Mapping(source = "response.statusText", target = "statusText")
    @Mapping(source = "response.responseBodySize", target = "responseBodySize")
    @Mapping(source = "response.responseHeadersSize", target = "responseHeadersSize")
    ApiLog dtoToModel(ApiDto apiDto);
}
