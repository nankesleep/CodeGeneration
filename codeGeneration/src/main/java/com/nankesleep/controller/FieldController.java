package com.nankesleep.controller;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Field;
import com.nankesleep.domain.entity.Table;
import com.nankesleep.service.FieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/field")
@Api(tags = "字段",description = "字段相关接口")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    //分页查询

    @GetMapping("/getFieldList")
    @ApiOperation(value = "分页查询字段",notes = "获取一页字段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "表id"),
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult getFieldList(Long id,Integer pageNum,Integer pageSize){
        return fieldService.getFieldList(id,pageNum,pageSize);
    }

    //单个查询
    @GetMapping("/getFieldById/{id}")
    @ApiOperation(value = "查询字段",notes = "获取某一个字段信息")
    @ApiImplicitParam(name = "id",value = "表id")
    public ResponseResult getByFieldId(@PathVariable("id") Long id){
        return fieldService.getByFieldId(id);
    }


    //逻辑删除
    @DeleteMapping("/deleteField/{id}")
    @ApiOperation(value = "删除字段",notes = "删除某一个字段信息")
    @ApiImplicitParam(name = "id",value = "表id")
    public ResponseResult deleteField(@PathVariable("id") Long id){
        return fieldService.deleteField(id);
    }

    //修改信息
    @PutMapping("/updateField")
    @ApiOperation(value = "修改字段",notes = "修改某一个字段信息")
    @ApiImplicitParam(name = "field",value = "Field对象")
    public ResponseResult updateField(@RequestBody Field field){
        return fieldService.updateField(field);
    }

    //添加项目
    @PostMapping("/addField")
    @ApiOperation(value = "新增字段",notes = "新增某一个字段信息")
    @ApiImplicitParam(name = "field",value = "Field对象")
    public ResponseResult addField(@RequestBody Field field){
        return fieldService.addField(field);
    }
}
