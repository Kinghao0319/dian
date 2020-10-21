package com.kinghao.dian.aspect;

import com.kinghao.dian.annotation.ReSubmit;
import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.common.CommonException;
import com.kinghao.dian.common.ReSubmitLock;
import com.kinghao.dian.dto.BaseRequest;
import com.kinghao.dian.util.AssertUtil;
import com.kinghao.dian.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据重复提交校验
 * @author kinghao
 * @version 2020/10/26 15:00
 **/
@Aspect
@Component
public class ResubmitDataAspect {

    private final static Object PRESENT = new Object();

    @Around("@annotation(com.kinghao.dian.annotation.ReSubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ReSubmit annotation = method.getAnnotation(ReSubmit.class);
        long delayMilliseconds = annotation.value();
        Object[] pointArgs = joinPoint.getArgs();
        if(pointArgs.length == 0){
            return joinPoint.proceed();
        }
        Object param = pointArgs[0];
        AssertUtil.isTrue(param instanceof BaseRequest,CommonErrorCode.NOT_INSTANCEOF_BASE_REQUEST,"请求必须是BaseRequest类型");

        String key = ReSubmitLock.handleKey(
                JsonUtil.toJSONString(param)
        );

        boolean lock = false;
        try {
            lock = ReSubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                return joinPoint.proceed();
            } else {
                throw new CommonException(CommonErrorCode.RESUBMIT_ERROR,"invoke method: " + method + "should delay request for:" + delayMilliseconds + "ms");
            }
        } finally {
            ReSubmitLock.getInstance().unLock(lock, key, delayMilliseconds);
        }
    }
}