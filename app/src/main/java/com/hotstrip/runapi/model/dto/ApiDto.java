package com.hotstrip.runapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ApiDto {
    private String url;
    private String method;
    private Request request;
    private Response response;

}
