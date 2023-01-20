package com.nankesleep.domain.entity;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 字段信息表(Field)表实体类
 *
 * @author nankesleep
 * @since 2023-01-14 20:03:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_field")
public class Field  {
    //主键    
    @TableId
    private Long id;

    //关联cg_table表
    private Long pid;
    //字段名称
    private String fieldName;
    //字段数
    @TableField(exist = false)
    private Integer fieldCount;
    //字段类型
    private String type;
    //长度
    private String length;
    //备注
    private String remark;
    //默认值
    private String defaultValue;
    //键类型（0无，1主键，2唯一索引，3普通索引）
    private String keyType;
    //主键自增（0无，1是）
    private String isAuto;
    //允许为null（0是，1否）
    private String isNull;
    //功能集合
    @TableField(exist = false)
    private List function;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;



}
