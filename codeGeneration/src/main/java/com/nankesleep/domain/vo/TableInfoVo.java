package com.nankesleep.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfoVo {
    private Long id;
    private String tableName;
    private String remark;
}
