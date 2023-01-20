package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.LoginUser;
import com.nankesleep.domain.entity.User;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.domain.vo.UserInfoVo;
import com.nankesleep.domain.vo.UserLoginVo;
import com.nankesleep.untils.BeanCopyUtils;
import com.nankesleep.untils.JwtUtil;
import com.nankesleep.untils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 用户项目关联表(UserProject)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-12 20:22:32
 */
public interface UserProjectService extends IService<UserProject> {

    ResponseResult getProjectUser(Long id);


    ResponseResult updateAdmin(UserProject userProject);

    ResponseResult deleteUser(Long userId, Long projectId);

    ResponseResult addProject(List<UserProject> userProject);

    ResponseResult getUser(Long id, String key);
}

