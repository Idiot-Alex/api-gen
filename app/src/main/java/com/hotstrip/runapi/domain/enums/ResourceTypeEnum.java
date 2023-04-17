package com.hotstrip.runapi.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源类型枚举
 * @author Hotstrip
 * @since 2023-04-17
 */
public enum ResourceTypeEnum {
    DOCUMENT("document", "HTML 文档类型"),
    STYLESHEET("stylesheet", "CSS 样式表类型"),
    IMAGE("image", "图片类型"),
    MEDIA("media", "音视频类型"),
    FONT("font", "字体文件类型"),
    SCRIPT("script", "JavaScript 脚本类型"),
    TEXTTRACK("texttrack", "字幕或其他带有文本的轨道（如 VTT）类型"),
    XHR("xhr", "XMLHttpRequest 类型"),
    FETCH("fetch", "Fetch 类型"),
    EVENT_SOURCE("eventsource", "EventSource 类型"),
    WEBSOCKET("websocket", "WebSocket 类型"),
    MANIFEST("manifest", "Web App Manifest 文件类型"),
    OTHER("other", "其他类型");

    private final String type;
    private final String description;

    ResourceTypeEnum(String type, String description) {
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
        return Arrays.stream(ResourceTypeEnum.values())
                .map(ResourceTypeEnum::getType)
                .collect(Collectors.toList());
    }

    // 检测是否存在 type 值为 type 的枚举
    public static boolean isExistType(String type) {
        return getTypeList().contains(type);
    }
}
