package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.entity.User;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.mapper.UserMapper;
import com.nankesleep.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息表
(User)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-11 13:08:25
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getProjectUser(UserProject userProject) {
        User user = getById(userProject.getUserId());
        user.setPower(userProject.getPower());
        user.setIsAdmin(userProject.getIsAdmin());
        return user;
    }
}
