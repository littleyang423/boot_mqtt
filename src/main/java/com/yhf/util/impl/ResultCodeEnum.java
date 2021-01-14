package com.yhf.util.impl;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Getter;

/**
 * @author yhf
 * @date -
 */
@Getter
@ToString
@AllArgsConstructor
public enum ResultCodeEnum {

    UNKNOWN_ERROR("404", "找不到网页"),
    ERROR("400","失败"),
    SUCCESS("200", "成功"),
    USER_NOT_EXIST("A001", "用户不存在"),
    USER_IS_EXISTS("A002", "用户已存在"),
    VCODE_ERROR("A003","验证码错误"),
    LOGIN_EXPIRED("A004","用户未登录"),
    PWD_ERROR("A005","密码错误"),
    NO_TOKEN("A006","未提供token"),
    INVALID_TOKEN("A007","token无效"),
    EXPIRED_TOKEN("A008","token过期"),
    REPLACE_TOKEN("A009","token被顶下线"),
    DATA_IS_NULL("B001", "数据为空"),
    PARAM_ERROR("C001","参数错误")
    ;

    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
