package com.kinghao.dian.aspect;

import com.kinghao.dian.annotation.Auth;
import com.kinghao.dian.common.CommonConstants;
import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.SessionData;
import com.kinghao.dian.enums.UserType;
import com.kinghao.dian.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 权限控制
 * @author kinghao
 * @version 2020/8/01 17:06
 */
@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    HttpSession session;

    @Around("@annotation(com.kinghao.dian.annotation.Auth)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        Auth annotation = method.getAnnotation(Auth.class);
        UserType type = annotation.value();

        final SessionData sessionData = (SessionData)session.getAttribute(CommonConstants.SESSION);
        AssertUtil.notNull(sessionData, CommonErrorCode.SESSION_TIMEOUT,"会话超时");
        AssertUtil.equals(type.getType(),sessionData.getType(),CommonErrorCode.PERMISSION_DENIED
                ,"权限不足，expire userType = " + type.getType() + ", actual userType = " + sessionData.getType());

        return joinPoint.proceed();

    }


}
