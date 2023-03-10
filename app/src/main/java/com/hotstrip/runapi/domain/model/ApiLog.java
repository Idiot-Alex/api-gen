package com.hotstrip.runapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ApiLog {
    private Long id;
    private String url;
    private String method;

    private String resourceType;
    private String requestHeaders;
    private String postData;
    private Boolean failed;
    private String errorText;
    private Integer requestBodySize;
    private Integer requestHeadersSize;

    private String responseHeaders;
    private String text;
    private Integer status;
    private String statusText;
    private Integer responseBodySize;
    private Integer responseHeadersSize;

    private Date createTime;
}
