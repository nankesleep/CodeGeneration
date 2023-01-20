package com.nankesleep.controller;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.Table;
import com.nankesleep.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
@Api(tags = "表",description = "表相关接口")
public class TableController {

    @Autowired
    private TableService tableService;

    //分页查询表
    @GetMapping("/getTableList")
    @ApiOperation(value = "分页查询表",notes = "获取一页表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "项目id"),
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult getTableList(Long id,Integer pageNum,Integer pageSize){
        return tableService.getTableList(id,pageNum,pageSize);
    }

    //单个查询
    @GetMapping("/getTableById/{id}")
    @ApiOperation(value = "查询表",notes = "查询某一个表信息")
    @ApiImplicitParam(name = "id",value = "表id")
    public ResponseResult getTableById(@PathVariable("id") Long id){
        return tableService.getTableById(id);
    }

    //逻辑删除
    @DeleteMapping("/deleteTable/{id}")
    @ApiOperation(value = "删除表",notes = "删除某一个表信息")
    @ApiImplicitParam(name = "id",value = "表id")
    public ResponseResult deleteTable(@PathVariable("id") Long id){
        return tableService.deleteTable(id);
    }

    //修改信息
    @PutMapping("/updateTable")
    @ApiOperation(value = "修改表",notes = "修改某一个表信息")
    @ApiImplicitParam(name = "table",value = "Table对象")
    public ResponseResult updateTable(@RequestBody Table table){
        return tableService.updateTable(table);
    }

    //添加表
    @PostMapping("/addTable")
    @ApiOperation(value = "新增表",notes = "新增某一个表信息")
    @ApiImplicitParam(name = "table",value = "Table对象")
    public ResponseResult addTable(@RequestBody Table table){
        return tableService.addTable(table);
    }

}
