package com.nankesleep.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 功能信息表
(Function)表实体类
 *
 * @author nankesleep
 * @since 2023-01-16 19:18:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_function")
public class Function  {
    //主键    
    @TableId
    private Integer id;

    //功能名称
    private String functionName;
    //功能描述
    private String remark;
    //功能类型（0基础功能，1查询条件，2字段含义）
    private String type;



}
