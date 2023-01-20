package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.entity.FieldFunction;

import java.util.List;


/**
 * 字段功能关联表(FieldFunction)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-16 19:20:02
 */
public interface FieldFunctionService extends IService<FieldFunction> {
    List getFunctionName(Long id);
}

