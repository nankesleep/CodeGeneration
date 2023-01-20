package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Project;
import com.nankesleep.domain.entity.User;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.domain.vo.PageVo;
import com.nankesleep.domain.vo.ProjectVo;
import com.nankesleep.domain.vo.UserAdminVo;
import com.nankesleep.domain.vo.UserProjectVo;
import com.nankesleep.handler.exception.SystemException;
import com.nankesleep.mapper.UserProjectMapper;
import com.nankesleep.service.ProjectService;
import com.nankesleep.service.UserProjectService;
import com.nankesleep.service.UserService;
import com.nankesleep.untils.BeanCopyUtils;
import com.nankesleep.untils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 用户项目关联表(UserProject)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-12 20:22:32
 */
@Service("userProjectService")
public class UserProjectServiceImpl extends ServiceImpl<UserProjectMapper, UserProject> implements UserProjectService {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    //查询项目用户
    @Override
    public ResponseResult getProjectUser(Long id) {
        //查询项目用户id
        LambdaQueryWrapper<UserProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserProject::getProjectId, id);
        queryWrapper.orderByAsc(UserProject::getIsAdmin);
        List<UserProject> userProjectList = list(queryWrapper);
        //查询用户信息
        List<User> userList = new ArrayList<>();
        userProjectList.stream().forEach(userProject -> userList.add(userService.getProjectUser(userProject)));
        List<UserProjectVo> userProjectVos = BeanCopyUtils.copyBeanList(userList, UserProjectVo.class);
        //判断该用户是管理员还是成员
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        Integer isAdmin = userList.get(0).getId() == userId ? 0 : 1;
        UserAdminVo userAdminVo = new UserAdminVo(userProjectVos, isAdmin);
        return ResponseResult.okResult(userAdminVo);
    }

    //移交项目管理员
    @Override
    public ResponseResult updateAdmin(UserProject userProject) {

        LambdaUpdateWrapper<UserProject> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(UserProject::getUserId, userProject.getUserId());
        lambdaUpdateWrapper.eq(UserProject::getProjectId, userProject.getProjectId());

        if (userProject.getPower() != null) {
            //修改成员权限（可编辑，可查看）
            lambdaUpdateWrapper.set(UserProject::getPower, userProject.getPower());
            update(new UserProject(), lambdaUpdateWrapper);
        } else {
            //移交项目
            //设置该用户为管理员
            lambdaUpdateWrapper.set(UserProject::getPower, "0");
            lambdaUpdateWrapper.set(UserProject::getIsAdmin, "0");
            update(new UserProject(), lambdaUpdateWrapper);

            //移除当前用户为管理员
            Long userId = null;
            try {
                userId = SecurityUtils.getUserId();
            } catch (SystemException e) {
                e.printStackTrace();
            }
            LambdaUpdateWrapper<UserProject> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(UserProject::getUserId, userId);
            updateWrapper.eq(UserProject::getProjectId, userProject.getProjectId());
            updateWrapper.set(UserProject::getPower, "0");
            updateWrapper.set(UserProject::getIsAdmin, "1");
            update(new UserProject(), updateWrapper);

            //更新project中的manageby
            LambdaUpdateWrapper<Project> projectLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            projectLambdaUpdateWrapper.eq(Project::getId, userProject.getProjectId());
            projectLambdaUpdateWrapper.set(Project::getManageBy,userProject.getUserId());
            projectService.update(new Project(), projectLambdaUpdateWrapper);
        }
        return ResponseResult.okResult();

    }

    //删除成员
    @Override
    public ResponseResult deleteUser(Long userId, Long projectId) {
        LambdaQueryWrapper<UserProject> userProjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userProjectLambdaQueryWrapper.eq(UserProject::getUserId,userId);
        userProjectLambdaQueryWrapper.eq(UserProject::getProjectId,projectId);
        remove(userProjectLambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addProject(List<UserProject> userProject) {
        saveBatch(userProject);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUser(Long id, String key) {
        System.out.println(getProjectUser(id).getData().toString());
        return null;
    }
}
