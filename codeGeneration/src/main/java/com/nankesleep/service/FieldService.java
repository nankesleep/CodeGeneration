package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Field;


/**
 * 字段信息表(Field)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-14 20:03:19
 */
public interface FieldService extends IService<Field> {

    ResponseResult getFieldList(Long id, Integer pageNum, Integer pageSize);

    ResponseResult getByFieldId(Long id);

    ResponseResult deleteField(Long id);

    ResponseResult updateField(Field field);

    ResponseResult addField(Field field);
}

