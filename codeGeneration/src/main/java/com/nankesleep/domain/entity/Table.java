package com.nankesleep.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表信息表(Table)表实体类
 *
 * @author nankesleep
 * @since 2023-01-10 19:58:54
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_table")
public class Table  {
    //主键    
    @TableId
    private Long id;
    //关联cg_project表
    private Long pid;
    //表数量
    @TableField(exist = false)
    private Integer tableNumber;
    //表名称
    private String tableName;
    //备注
    private String remark;
    //字段数
    @TableField(exist = false)
    private Integer fieldCount;
    //创建人员
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //创建人名称
    @TableField(exist = false)
    private String createName;
    //更新人员
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    //更新人员名称
    @TableField(exist = false)
    private String updateName;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;


}
