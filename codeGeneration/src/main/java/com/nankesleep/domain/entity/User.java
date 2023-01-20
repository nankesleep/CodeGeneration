package com.nankesleep.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户信息表
(User)表实体类
 *
 * @author nankesleep
 * @since 2023-01-11 13:08:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_user")
public class User  {
    //主键    
    @TableId
    private Long id;

    //用户名
    private String userName;
    //昵称
    private String nickName;
    //密码
    private String password;
    //账号状态（0正常，1停用）
    private String status;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //用户性别（0男，1女，2未知）
    private String sex;
    @TableField(exist = false)
    //管理权限（0可编辑，1可查看）
    private String power;
    @TableField(exist = false)
    //是否为项目管理员（0是，1否）
    private String isAdmin;
    //用户头像
    private String avatar;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}
