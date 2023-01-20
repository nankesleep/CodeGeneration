package com.nankesleep.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableVo {
    //主键
    private Long id;
    //表名称
    private String tableName;
    //备注
    private String remark;
    //字段数
    private Integer fieldCount;
    //创建人
    private String createName;
    //更新人
    private String updateName;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
