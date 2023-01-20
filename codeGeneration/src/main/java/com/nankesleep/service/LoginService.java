package com.nankesleep.service;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
