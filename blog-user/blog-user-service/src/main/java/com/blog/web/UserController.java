package com.blog.web;

import com.blog.UserService;
import com.blog.exception.MyValidException;
import com.blog.model.User;
import com.blog.vo.ResultBean;
import com.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.web
 * @Author: ly
 * @CreateTime: 2019-08-27 11:24
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("/send/{phone}")
    public ResponseEntity<ResultBean> sendVerifyCode(@PathVariable("phone") String phone) {
        userService.sendVerifyCode(phone);
        return ResponseEntity.ok(ResultBean.success("发送验证码成功!"));
    }

    /**
     * 用户注册
     *
     * @param userVo
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<ResultBean> register(@Valid UserVo userVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyValidException(bindingResult);
        }
        userService.register(userVo);
        return ResponseEntity.ok(ResultBean.success("注册成功!"));
    }


    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @PostMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(userService.queryUser(username, password));
    }

}
