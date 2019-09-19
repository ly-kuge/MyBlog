package com.blog;

import com.blog.model.User;
import com.blog.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface UserService {

    /**
     * 用户注册
     * @param userVo
     */


    @PostMapping
    void register(@Valid UserVo userVo);

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("/send/{phone}")
    void sendVerifyCode(@PathVariable("phone") String phone);


    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @PostMapping("query")
     User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password);

}

