package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.entity.User;
import com.nankesleep.domain.entity.UserProject;


/**
 * 用户信息表
(User)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-11 13:08:24
 */
public interface UserService extends IService<User> {
    User getProjectUser(UserProject userProject);
}

