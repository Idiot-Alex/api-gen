package com.hotstrip.runapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Request {
    private String resourceType;
    private HashMap<String, String> headers;
    private String postData;
    private Boolean failed;
    private String errorText;
    private Integer requestBodySize;
    private Integer requestHeadersSize;
}
