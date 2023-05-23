package com.hotstrip.runapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ApiDocDto {
    private String name;
    private String description;
    private List<String> apiList;
}
