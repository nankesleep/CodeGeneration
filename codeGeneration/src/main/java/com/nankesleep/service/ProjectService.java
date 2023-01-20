package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.User;


/**
 * 项目信息表(Project)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-10 20:57:25
 */
public interface ProjectService extends IService<Project> {

    ResponseResult getProject();

    ResponseResult getProjectById(Long id);

    ResponseResult getProjectById(Long id,Integer tableNumber);


    ResponseResult getProjectList(Integer pageNum, Integer pageSize);

    ResponseResult getProjectByStatus(String status, Integer pageNum, Integer pageSize);

    ResponseResult search(String key);

    ResponseResult deleteProject(Long id);

    ResponseResult updateProject(Project project);

    ResponseResult addProject(Project project);

    ResponseResult verifyPassword(User user);
}

