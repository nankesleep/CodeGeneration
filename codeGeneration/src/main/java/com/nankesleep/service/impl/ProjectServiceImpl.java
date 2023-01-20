package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.Table;
import com.nankesleep.domain.entity.User;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.domain.vo.PageVo;
import com.nankesleep.domain.vo.ProjectVo;
import com.nankesleep.enums.AppHttpCodeEnum;
import com.nankesleep.handler.exception.SystemException;
import com.nankesleep.mapper.ProjectMapper;
import com.nankesleep.service.ProjectService;
import com.nankesleep.service.TableService;
import com.nankesleep.service.UserProjectService;
import com.nankesleep.service.UserService;
import com.nankesleep.untils.BeanCopyUtils;
import com.nankesleep.untils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

/**
 * 项目信息表(Project)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-10 20:57:25
 */
@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private TableService tableService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserProjectService userProjectService;

    //查询项目中管理员，创建人，更新人的姓名
    public Project getNickName(Project project) {
        project.setManageName(userService.getById(project.getManageBy()).getNickName());
        project.setCreateName(userService.getById(project.getCreateBy()).getNickName());
        project.setUpdateName(userService.getById(project.getUpdateBy()).getNickName());
        return project;
    }

    //查询项目中的表数量
    public Project getTableNumber(Project project) {
        QueryWrapper<Table> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pid,count(*) as tableNumber");
        queryWrapper.eq("pid", project.getId());
        Table table = tableService.getOne(queryWrapper);
        project.setTableNumber(table.getTableNumber());
        return project;
    }

    //查询前5个表数多的项目
    @Override
    public ResponseResult getProject() {
        //获取userid
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        //通过userid查询出项目id
        LambdaQueryWrapper<UserProject> userProjectQueryWrapper = new LambdaQueryWrapper<>();
        userProjectQueryWrapper.eq(UserProject::getUserId, userId);
        List<UserProject> userProjectList = userProjectService.list(userProjectQueryWrapper);
        //通过项目id查询项目信息
        List<Project> projectList = new ArrayList<>();
        userProjectList.stream().forEach(userProject -> projectList.add(getById(userProject.getProjectId())));
        //查询表数量
        projectList.stream().forEach(project -> getTableNumber(project));
        //按照tableNumber从高到低排序
        List<Project> projectListSorted = new ArrayList<>();
        projectList.stream().sorted(Comparator.comparing(Project::getTableNumber).reversed()).forEach(project -> projectListSorted.add(project));
        //取前5个项目
        List<Project> projects = null;
        if (projectListSorted.size() < 5) {
            projects = projectListSorted;
        } else {
            projects = projectListSorted.subList(0, 5);
        }
        //设置项目中管理员，创建人，更新人的姓名
        projects.stream().forEach(project -> getNickName(project));
        //封装vo
        List<ProjectVo> projectVos = BeanCopyUtils.copyBeanList(projects, ProjectVo.class);
        return ResponseResult.okResult(projectVos);
    }

    //分页查询
    @Override
    public ResponseResult getProjectList(Integer pageNum, Integer pageSize) {
        //获取userid
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        //分页查询
        LambdaQueryWrapper<UserProject> userProjectQueryWrapper = new LambdaQueryWrapper<>();
        userProjectQueryWrapper.eq(UserProject::getUserId, userId);
        Page<UserProject> page = new Page<>(pageNum, pageSize);
        userProjectService.page(page, userProjectQueryWrapper);

        List<UserProject> userProjects = page.getRecords();

        List<Project> projectList = new ArrayList<>();
        userProjects.stream().forEach(userProject -> projectList.add(getById(userProject.getProjectId())));
        projectList.stream().forEach(this::getNickName);
        List<ProjectVo> projectVos = BeanCopyUtils.copyBeanList(projectList, ProjectVo.class);
        PageVo pageVo = new PageVo(projectVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }


    //根据id查询项目信息
    @Override
    public ResponseResult getProjectById(Long id) {
        Project project = getById(id);
        getNickName(project);
        ProjectVo projectVo = BeanCopyUtils.copyBean(project, ProjectVo.class);
        return ResponseResult.okResult(projectVo);
    }

    //项目状态分类查询
    @Override
    public ResponseResult getProjectByStatus(String status, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getStatus, status);
        //分页查询
        Page<Project> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Project> projectList = page.getRecords();
        projectList.stream().forEach(this::getNickName);
        List<ProjectVo> projectVos = BeanCopyUtils.copyBeanList(projectList, ProjectVo.class);
        PageVo pageVo = new PageVo(projectVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    //TODO 根据项目名称/项目描述/创建人等进行模糊搜索
    @Override
    public ResponseResult search(String key) {

        return ResponseResult.okResult();
    }


    //删除
    @Override
    public ResponseResult deleteProject(Long id) {
        //获取userid
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        LambdaQueryWrapper<UserProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserProject::getUserId,userId);
        queryWrapper.eq(UserProject::getProjectId,id);
        userProjectService.remove(queryWrapper);
        removeById(id);
        return ResponseResult.okResult();
    }

    //修改
    @Override
    public ResponseResult updateProject(Project project) {
        updateById(project);
        return ResponseResult.okResult();
    }

    //添加
    @Override
    public ResponseResult addProject(Project project) {
        //添加项目信息
        save(project);
        //添加用户项目信息
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        UserProject userProject = new UserProject();
        userProject.setUserId(userId);
        userProject.setProjectId(project.getId());
        userProject.setPower("0");
        userProject.setIsAdmin("1");
        userProjectService.save(userProject);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult verifyPassword(User user) {
        String rightPassword = "";
        try {
            rightPassword = SecurityUtils.getPassword();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean isRight = bCryptPasswordEncoder.matches(user.getPassword(),rightPassword);
        if (isRight==true){
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
    }


    @Override
    public ResponseResult getProjectById(Long id, Integer tableNumber) {
        Project project = projectService.getById(id);
        project.setTableNumber(tableNumber);
        project.setManageName(userService.getById(project.getManageBy()).getNickName());
        project.setCreateName(userService.getById(project.getCreateBy()).getNickName());
        project.setUpdateName(userService.getById(project.getUpdateBy()).getNickName());
        return ResponseResult.okResult(project);
    }

}
