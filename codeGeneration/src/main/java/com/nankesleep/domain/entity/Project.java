package com.nankesleep.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目信息表(Project)表实体类
 *
 * @author nankesleep
 * @since 2023-01-10 20:57:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_project")
public class Project  {
    //主键    
    @TableId
    private Long id;
    //项目名称
    private String projectName;
    //数据库类型（0MySQL，1oracle）
    private String type;
    //项目根路径
    private String location;
    //表前缀
    private String tablePrefix;
    //项目描述
    private String description;
    //项目状态（0进行中，1已完成）
    @TableField(fill = FieldFill.INSERT)
    private String status;
    //表数量
    @TableField(exist = false)
    private Integer tableNumber;
    //管理人员
    @TableField(fill = FieldFill.INSERT)
    private Long manageBy;
    //管理员名称
    @TableField(exist = false)
    private String manageName;
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
