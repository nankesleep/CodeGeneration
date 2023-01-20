package com.nankesleep.controller;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.service.UserProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("user")
@Api(tags = "项目成员",description = "项目成员相关接口")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    //查询项目成员
    @GetMapping("/getProjectUser/{id}")
    @ApiOperation(value = "查询成员",notes = "查询某一个项目中的成员信息")
    @ApiImplicitParam(name = "id",value = "项目id")
    public ResponseResult getProjectUser(@PathVariable("id") Long id){
        return userProjectService.getProjectUser(id);
    }

    //移交项目管理员
    @PutMapping("/updateAdmim")
    @ApiOperation(value = "移交权限",notes = "移交某一个项目中的成员的权限信息")
    @ApiImplicitParam(name = "userProject",value = "UserProject对象")
    public ResponseResult updateAdmin(@RequestBody UserProject userProject){
        return userProjectService.updateAdmin(userProject);
    }

    //删除成员
    @DeleteMapping("/deleteUser/{userId}/{projectId}")
    @ApiOperation(value = "删除成员",notes = "删除某一个项目中的成员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "projectId",value = "项目id")
    })
    public ResponseResult deleteUser(@PathVariable("userId") Long userId,@PathVariable("projectId") Long projectId){
        return userProjectService.deleteUser(userId,projectId);
    }

    //添加成员
    @PostMapping("/addUser")
    @ApiOperation(value = "新增成员",notes = "新增某一个项目中的成员信息")
    @ApiImplicitParam(name = "userProject",value = "UserProject集合")
    public ResponseResult addProject(@RequestBody List<UserProject> userProject){
        return userProjectService.addProject(userProject);
    }
}
