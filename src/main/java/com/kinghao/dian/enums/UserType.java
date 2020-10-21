package com.kinghao.dian.enums;

/**
 * @Author Kinghao
 * @Date 2020/10/5 12:12
 * @Version 1.0
 */
public enum UserType {
    PERSONAL("personal","个人账号"),
    ADMINISTRATION("administration","管理员账号")
    ;

    private String type;

    private String description;

    UserType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
