package com.hotstrip.runapi.domain.mapstruct;

import cn.hutool.core.util.IdUtil;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiDto;
import com.hotstrip.runapi.utils.JacksonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.net.URL;
import java.util.Date;

/**
 * use spring control
 * @author hotstrip
 * @since 2022-12-31
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class, JacksonUtil.class, Date.class})
public interface ApiLogMapstruct {

    @Mapping(target = "id", expression = "java(IdUtil.getSnowflake().nextId())")
    @Mapping(source = "url", target = "host", qualifiedByName = "getHostByUrl")
    @Mapping(source = "url", target = "site", qualifiedByName = "getSiteByUrl")
    @Mapping(source = "request.resourceType", target = "resourceType")
    @Mapping(target = "requestHeaders", expression = "java(com.hotstrip.runapi.utils.JacksonUtil.toJsonString(apiDto.getRequest().getHeaders()))")
    @Mapping(source = "request.postData", target = "postData")
    @Mapping(source = "request.failed", target = "failed")
    @Mapping(source = "request.errorText", target = "errorText")
    @Mapping(source = "request.requestBodySize", target = "requestBodySize")
    @Mapping(source = "request.requestHeadersSize", target = "requestHeadersSize")
    @Mapping(target = "responseHeaders", expression = "java(com.hotstrip.runapi.utils.JacksonUtil.toJsonString(apiDto.getResponse().getHeaders()))")
    @Mapping(source = "response.text", target = "text")
    @Mapping(source = "response.status", target = "status")
    @Mapping(source = "response.statusText", target = "statusText")
    @Mapping(source = "response.responseBodySize", target = "responseBodySize")
    @Mapping(source = "response.responseHeadersSize", target = "responseHeadersSize")
    @Mapping(target = "createTime", expression = "java(new Date())")
    ApiLog dtoToModel(ApiDto apiDto);

    @Named("getHostByUrl")
    default String getHostByUrl(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception e) {
            return "";
        }
    }

    @Named("getSiteByUrl")
    default String getSiteByUrl(String url) {
        try {
            URL urlObj = new URL(url);
            int port = urlObj.getPort() == -1 ? urlObj.getDefaultPort() : urlObj.getPort();
            return String.format("%s:%d", urlObj.getHost(), port);
        } catch (Exception e) {
            return "";
        }
    }
}
