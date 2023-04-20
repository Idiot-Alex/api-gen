package com.hotstrip.runapi.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参数类型枚举
 * @author Hotstrip
 * @since 2023-04-17
 */
public enum ParamTypeEnum {
    STRING("string", "字符串类型"),
    OBJECT("object", "对象类型"),
    ARRAY("array", "数组类型"),
    ;

    private final String type;
    private final String description;

    ParamTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static List<String> getTypeList() {
        return Arrays.stream(ParamTypeEnum.values())
                .map(ParamTypeEnum::getType)
                .collect(Collectors.toList());
    }

    // 检测是否存在 type 值为 type 的枚举
    public static boolean isExistType(String type) {
        return getTypeList().contains(type);
    }
}
