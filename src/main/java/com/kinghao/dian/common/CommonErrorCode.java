package com.kinghao.dian.common;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kinghao
 * @version 2020/7/23 0:51
 */
@Getter
public enum CommonErrorCode {
    SYSTEM_ERROR(1001,"系统错误","系统错误，请重试"),
    ILLEGAL_PARAMETER(1002,"参数不合法","系统错误，请重试"),
    NOT_INSTANCEOF_BASE_REQUEST(1003,"请求必须是BaseRequest类型","系统错误，请重试"),
    RESUBMIT_ERROR(1004,"提交太频繁","请勿重复提交"),
    SESSION_TIMEOUT(1005,"会话失效","请登录"),
    PERMISSION_DENIED(1006,"权限校验失败","权限不足，请升级为机构账户"),
    TEL_USED_ERROR(1007,"手机号已注册","请前往登录"),
    VERIFY_FAILED(1008,"验证失败","请重试"),
    LOGIN_FAILED(1009,"登录失败","用户名或密码错误"),
    PARAMS_INVALID(1010,"存在有误的参数","请重试"),
    UNSIGNED_USER(1011,"未注册用户","请前往注册"),
    INVALID_PHONE(1012,"无效手机号","请输入正确的手机号"),
    USERNAME_USED_ERROR(1013,"用户名已注册","请前往登录")

    ;

    /**
     * 错误码
     */
    private final Integer errorCode;

    /**
     * 错误原因（给开发看的）
     */
    private final String errorReason;

    /**
     * 错误行动指示（给用户看的）
     */
    private final String errorSuggestion;

    CommonErrorCode(Integer errorCode, String errorReason, String errorSuggestion) {
        this.errorCode = errorCode;
        this.errorReason = errorReason;
        this.errorSuggestion = errorSuggestion;
    }

    @Override
    public String toString() {
        return "CommonErrorCode{" +
                "errorCode=" + errorCode +
                ", errorReason='" + errorReason + '\'' +
                ", errorSuggestion='" + errorSuggestion + '\'' +
                '}';
    }

    //use for json serialization
    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode",errorCode);
        map.put("errorReason",errorReason);
        map.put("errorSuggestion",errorSuggestion);
        return map;
    }


}