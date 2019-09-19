package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ly
 * @date 2018/9/15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    PHONE_FORMAT_ERROR(500, "手机号码格式错误"),
    EXIST_USER(500,"该用户已存在"),
    USERNAME_OR_PASSWORD_ERROR(500, "账号或密码错误"),

    VERIFY_CODE_NOT_MATCHING(400, "验证码错误"),
    INVALID_PARAM(400, "参数错误"),


    ;
    int value;
    String message;

    public int value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }


}
