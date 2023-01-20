package com.nankesleep.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProjectVo {

    private Long id;
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //管理权限（0可编辑，1可查看）
    private String power;
    //是否为项目管理员（0是，1否）
    private String isAdmin;

}
