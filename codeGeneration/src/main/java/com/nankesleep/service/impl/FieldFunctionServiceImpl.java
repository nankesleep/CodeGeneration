package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.entity.Field;
import com.nankesleep.domain.entity.FieldFunction;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.mapper.FieldFunctionMapper;
import com.nankesleep.service.FieldFunctionService;
import com.nankesleep.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 字段功能关联表(FieldFunction)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-16 19:20:02
 */
@Service("fieldFunctionService")
public class FieldFunctionServiceImpl extends ServiceImpl<FieldFunctionMapper, FieldFunction> implements FieldFunctionService {

    @Autowired
    private FunctionService functionService;

    @Override
    public List getFunctionName(Long id) {
        LambdaQueryWrapper<FieldFunction> userProjectQueryWrapper = new LambdaQueryWrapper<>();
        userProjectQueryWrapper.eq(FieldFunction::getFieldId, id);
        List<FieldFunction> fieldFunctions = list(userProjectQueryWrapper);
        //通过项目id查询项目信息
        List functionNames = new ArrayList<>();
        fieldFunctions.stream().forEach(fieldFunction -> functionNames.add(functionService.getById(fieldFunction.getFunctionId()).getFunctionName()));
        return functionNames;
    }

}
