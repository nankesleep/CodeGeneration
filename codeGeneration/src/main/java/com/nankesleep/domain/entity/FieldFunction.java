package com.nankesleep.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 字段功能关联表(FieldFunction)表实体类
 *
 * @author nankesleep
 * @since 2023-01-16 19:20:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cg_field_function")
public class FieldFunction  {
    //字段id    
    private Long fieldId;
    //功能id    
    private Integer functionId;


}
