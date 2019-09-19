package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.vo
 * @Author: ly
 * @CreateTime: 2019-08-31 10:18
 * @Description:
 */

@Getter
@Setter
public class UserVo  {
    private String id;
    @Length(max = 30, min = 4, message = "昵称长度只能在4-30之间")
    private String nickname;//昵称
    @Length(max = 30, min = 4, message = "用户名长度只能在4-30之间")
    private String username;//用户名
    @JsonIgnore
    @Length(max = 30, min = 4, message = "密码长度只能在4-30之间")
    private String password;//密码
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;//电话号码
    @NotNull(message = "验证码不能为空")
    private String code;//验证码

}
