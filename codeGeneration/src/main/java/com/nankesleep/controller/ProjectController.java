package com.nankesleep.controller;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.User;
import com.nankesleep.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@Api(tags = "项目",description = "项目相关接口")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //查询前5个表数多的项目
    @GetMapping("/getProject")
    @ApiOperation(value = "查询项目",notes = "查询前5个表数多的项目信息")
    public ResponseResult getProject(){
        return projectService.getProject();
    }

    //分页查询
    @GetMapping("/getProjectList")
    @ApiOperation(value = "分页查询项目",notes = "获取一页项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult getProjectList(Integer pageNum,Integer pageSize){
        return projectService.getProjectList(pageNum,pageSize);
    }

    //根据id查询项目信息
    @GetMapping("/getProjectById/{id}")
    @ApiOperation(value = "查询项目",notes = "查询某一个项目信息")
    @ApiImplicitParam(name = "id",value = "项目id")
    public ResponseResult getProjectById(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }

    //项目状态分类查询
    @GetMapping("/getProjectByStatus")
    @ApiOperation(value = "状态查询",notes = "查询某一个状态的全部项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "状态"),
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult getProjectByStatus(String status,Integer pageNum,Integer pageSize){
        return projectService.getProjectByStatus(status,pageNum,pageSize);
    }

    //逻辑删除
    @DeleteMapping("/deleteProject/{id}")
    @ApiOperation(value = "删除项目",notes = "删除某一个项目信息")
    @ApiImplicitParam(name = "id",value = "项目id")
    public ResponseResult deleteProject(@PathVariable("id") Long id){
        return projectService.deleteProject(id);
    }

    //修改信息
    @PutMapping("/updateProject")
    @ApiOperation(value = "修改项目",notes = "修改某一个项目信息")
    @ApiImplicitParam(name = "project",value = "Project对象")
    public ResponseResult updateProject(@RequestBody Project project){
        return projectService.updateProject(project);
    }

    //添加项目
    @PostMapping("/addProject")
    @ApiOperation(value = "新增项目",notes = "新增某一个项目信息")
    @ApiImplicitParam(name = "project",value = "Project对象")
    public ResponseResult addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PostMapping("/verifyPassword")
    @ApiOperation(value = "验证密码",notes = "验证密码是否正确")
    @ApiImplicitParam(name = "user",value = "User对象")
    public ResponseResult verifyPassword(@RequestBody User user){ return projectService.verifyPassword(user);}

}
