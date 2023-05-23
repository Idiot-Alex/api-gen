package com.hotstrip.runapi.domain.mapstruct;

import cn.hutool.core.util.IdUtil;
import com.hotstrip.runapi.domain.model.ApiDoc;
import com.hotstrip.runapi.domain.model.dto.ApiDocDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * use spring control
 * @author hotstrip
 * @since 2023-05-23
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class, Date.class})
public interface ApiDocMapstruct {

    @Mapping(target = "id", expression = "java(IdUtil.getSnowflake().nextId())")
    @Mapping(source = "apiList", target = "apiCount", qualifiedByName = "getApiCount")
    @Mapping(target = "createTime", expression = "java(new Date())")
    ApiDoc dtoToModel(ApiDocDto apiDocDto);

    @Named("getApiCount")
    default int getApiCount(List<String> apiList) {
        return apiList.size();
    }
}
