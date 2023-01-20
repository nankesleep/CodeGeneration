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
public class ProjectVo {
    //主键
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
    private String status;
    //表数量
    private Integer tableNumber;
    //管理人员
    private String manageName;
    //创建人员
    private String createName;
    //更新人员
    private String updateName;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
