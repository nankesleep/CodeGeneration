package com.nankesleep.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldVo {
    private Long id;
    private Long pid;
    private String fieldName;
    private String type;
    private String length;
    private String remark;
    private String defaultValue;
    private List function;
    private String keyType;
    private String isAuto;
    private String isNull;
    private Integer functionId;
}
