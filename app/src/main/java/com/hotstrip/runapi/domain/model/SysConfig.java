package com.hotstrip.runapi.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@TableName("sys_config")
@Builder
public class SysConfig {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String paramKey;
    private String paramValue;
    private String description;
    private Date createTime;

    // 以下字段不存储到数据库
    @TableField(exist = false)
    private String idStr;

    public String getIdStr() {
        if (null == id)
            return "";
        return id.toString();
    }
}
