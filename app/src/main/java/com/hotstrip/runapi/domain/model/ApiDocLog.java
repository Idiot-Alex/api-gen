package com.hotstrip.runapi.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@TableName("api_doc_log")
public class ApiDocLog {
    @TableId(type = IdType.INPUT)
    private Long id;
    private Long docId;
    private Long apiId;
    private String url;
    private String host;
    private String site;
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

    // 以下字段不存储到数据库
    @TableField(exist = false)
    private String idStr;
    @TableField(exist = false)
    private String docIdStr;
    @TableField(exist = false)
    private String apiIdStr;

    public String getIdStr() {
        if (null == id)
            return "";
        return id.toString();
    }
    public String getDocIdStr() {
        if (null == docId)
            return "";
        return docId.toString();
    }
    public String getApiIdStr() {
        if (null == apiId)
            return "";
        return apiId.toString();
    }

}
