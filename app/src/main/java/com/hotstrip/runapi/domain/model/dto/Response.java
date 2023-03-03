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
public class Response {
    private HashMap<String, String> headers;
    private String text;
    private Integer status;
    private String statusText;
    private Integer responseBodySize;
    private Integer responseHeadersSize;

}
