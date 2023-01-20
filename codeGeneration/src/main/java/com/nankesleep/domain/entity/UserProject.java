package com.nankesleep.domain.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户项目关联表(UserProject)表实体类
 *
 * @author nankesleep
 * @since 2023-01-12 20:22:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_user_project")
public class UserProject  {
    //用户id
    private Long userId;
    //项目id
    private Long projectId;
    //管理权限（0可编辑，1可查看）
    private String power;
    //是否为项目管理员（0是，1否）
    @TableField(fill = FieldFill.INSERT)
    private String isAdmin;
    //创建人员
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //更新人员
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
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;

}
