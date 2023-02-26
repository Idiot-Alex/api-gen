package com.hotstrip.runapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Api {
    private String url;
    private String method;
    private Request request;
    private Response response;


}
