package com.hotstrip.runapi.model;

public class ApiLog {
    private Long id;
    private String url;
    private String method;

    private String resourceType;
    private String requestHeaders;
    private String postData;
    private Boolean failed;
    private String errorText;

    private String responseHeaders;
    private String text;
    private Integer status;
    private String statusText;
}
