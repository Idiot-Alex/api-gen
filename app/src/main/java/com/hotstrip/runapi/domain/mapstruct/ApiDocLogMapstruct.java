package com.hotstrip.runapi.domain.mapstruct;

import cn.hutool.core.util.IdUtil;
import com.hotstrip.runapi.domain.model.ApiDocLog;
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
 * @since 2023-05-22
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class})
public interface ApiDocLogMapstruct {

    @Mapping(target = "id", expression = "java(IdUtil.getSnowflake().nextId())")
    @Mapping(source = "id", target = "apiId")
    ApiDocLog apiLogToModel(ApiLog apiLog);

}
