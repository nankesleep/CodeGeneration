package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Field;
import com.nankesleep.domain.entity.FieldFunction;
import com.nankesleep.domain.entity.Table;
import com.nankesleep.domain.vo.FieldVo;
import com.nankesleep.domain.vo.PageVo;
import com.nankesleep.domain.vo.TableInfoVo;
import com.nankesleep.domain.vo.TableVo;
import com.nankesleep.mapper.FieldMapper;
import com.nankesleep.service.FieldFunctionService;
import com.nankesleep.service.FieldService;
import com.nankesleep.untils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * 字段信息表(Field)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-14 20:03:19
 */
@Service("fieldService")
public class
FieldServiceImpl extends ServiceImpl<FieldMapper, Field> implements FieldService {

    @Autowired
    private FieldFunctionService fieldFunctionService;

    //分页查询
    @Override
    public ResponseResult getFieldList(Long id, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Field> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Field::getPid,id);
        //分页查询
        Page<Field> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Field> fieldList = page.getRecords();
        fieldList.stream().forEach(field -> field.setFunction(fieldFunctionService.getFunctionName(field.getId())));
        //封装vo
        List<FieldVo> fieldVos = BeanCopyUtils.copyBeanList(fieldList, FieldVo.class);
        PageVo pageVo = new PageVo(fieldVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    //单个查询
    @Override
    public ResponseResult getByFieldId(Long id) {
        Field field =  getById(id);
        field.setFunction(fieldFunctionService.getFunctionName(id));
        FieldVo fieldVo = BeanCopyUtils.copyBean(field, FieldVo.class);
        return ResponseResult.okResult(fieldVo);
    }

    //删除表
    @Override
    public ResponseResult deleteField(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    //修改表
    @Override
    public ResponseResult updateField(Field field) {
        updateById(field);
        return ResponseResult.okResult();
    }

    //添加表
    @Override
    public ResponseResult addField(Field field) {
        save(field);
        return ResponseResult.okResult();
    }
}
