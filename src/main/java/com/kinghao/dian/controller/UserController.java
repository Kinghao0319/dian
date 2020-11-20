package com.kinghao.dian.controller;

import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.SessionData;
import com.kinghao.dian.dto.request.LoginRequest;
import com.kinghao.dian.dto.request.RegisterRequest;
import com.kinghao.dian.dto.request.UpdateUserInfoRequest;
import com.kinghao.dian.entity.User;
import com.kinghao.dian.enums.UserType;
import com.kinghao.dian.service.UserService;
import com.kinghao.dian.util.AssertUtil;
import com.kinghao.dian.util.PhoneNumUtil;
import com.kinghao.dian.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Kinghao
 * @Date 2020/10/21 16:33
 * @Version 1.0
 */
@Api(tags = "用户操作")
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {
    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Object register(@Valid RegisterRequest registerRequest){
        //检验合法手机号
        AssertUtil.isTrue(PhoneNumUtil.checkValid(registerRequest.getTel()),CommonErrorCode.INVALID_PHONE);
        //检验账户类型
        AssertUtil.isTrue(registerRequest.getType().equals(UserType.PERSONAL.getType())
                ||registerRequest.getType().equals(UserType.ADMINISTRATION.getType()),CommonErrorCode.PARAMS_INVALID);
        //检查性别
        AssertUtil.isTrue(registerRequest.getGender()==null
                ||registerRequest.getGender().equals("男")
                ||registerRequest.getGender().equals("女"), CommonErrorCode.PARAMS_INVALID);

        userService.userRegister(registerRequest);
        return "Register successfully!";
    }

    @ApiOperation(value = "用户登录",response = User.class)
    @PostMapping("/login")
    public Object login(LoginRequest loginRequest){
        User loginUser=userService.userLogin(loginRequest);
        if(loginUser!=null){
            loginUser.setPassword("");//隐藏密码返回
            sessionUtil.putSessionData(SessionData
                    .builder()
                    .id(loginUser.getId())
                    .tel(loginUser.getTel())
                    .username(loginUser.getUsername())
                    .type(loginUser.getType())
                    .build());
            log.info("User_"+sessionUtil.getSessionData().getUsername()+" login");
        }
        return loginUser;
    }

    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public Object logout(){
        sessionUtil.invalidate();
        return "Logout successfully!";
    }

    @ApiOperation(value = "修改当前用户信息（不修改的字段不发送，需要session）")//不修改的字段填入空，非空的字段同样按照注解上的参数校验规则进行
    @PutMapping("/update")
    public Object updateUserInfo(@Valid UpdateUserInfoRequest updateUserInfoRequest){
        //验证机构名
        AssertUtil.isTrue(updateUserInfoRequest.getType()==null
                ||updateUserInfoRequest.getType().equals(UserType.PERSONAL.getType())
                ||updateUserInfoRequest.getType().equals(UserType.ADMINISTRATION.getType()),CommonErrorCode.PARAMS_INVALID);

        //检查性别
        AssertUtil.isTrue(updateUserInfoRequest.getGender()==null
                ||updateUserInfoRequest.getGender().equals("男")
                ||updateUserInfoRequest.getGender().equals("女"),CommonErrorCode.PARAMS_INVALID);

        //如果用户未登录，sessionUtil或许不是null，但是真session返回的内容一定是null，所以判断getSessionData()
        SessionData sessionData=sessionUtil.getSessionData();
        AssertUtil.isTrue(sessionData!=null,CommonErrorCode.SESSION_TIMEOUT);
        userService.updateInfo(updateUserInfoRequest,sessionData);
        return null;
    }

    @ApiOperation(value = "根据用户名查询用户信息")
    @GetMapping("/query")
    public Object queryUserInfo(String username){
        User rt=userService.queryInfo(username);
        if(rt==null){
            return "Not Found!";
        }
        return rt;
    }
}
