package com.kinghao.dian.service;

import com.kinghao.dian.dto.SessionData;
import com.kinghao.dian.dto.request.LoginRequest;
import com.kinghao.dian.dto.request.RegisterRequest;
import com.kinghao.dian.dto.request.UpdateUserInfoRequest;
import com.kinghao.dian.entity.User;

/**
 * @Author Kinghao
 * @Date 2020/10/21 16:37
 * @Version 1.0
 */
public interface UserService {
    void userRegister(RegisterRequest registerRequest);

    User userLogin(LoginRequest loginRequest);

    void updateInfo(UpdateUserInfoRequest updateUserInfoRequest, SessionData sessionData);

    User queryInfo(String username);
}
