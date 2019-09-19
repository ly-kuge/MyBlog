package com.blog.web;

import com.blog.entity.UserInfo;
import com.blog.enums.ExceptionEnum;
import com.blog.exception.LyException;
import com.blog.properties.JwtProperties;
import com.blog.service.AuthService;
import com.blog.utils.CookieUtils;
import com.blog.utils.JwtUtils;
import com.blog.vo.ResultBean;
import com.blog.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.web
 * @Author: ly
 * @CreateTime: 2019-08-30 19:44
 * @Description:
 */
@RestController
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 登录授权
     * @param userVo
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<ResultBean> login(UserVo userVo,
                                            HttpServletRequest request,
                                            HttpServletResponse response
    ) {
        String token = authService.authentication(userVo);
        if (StringUtils.isBlank(token)) {
            log.error("用户账号或密码错误", "用户为----------------", userVo.getUsername());
            throw new LyException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //将Token写入cookie中
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), token);
        return ResponseEntity.ok(ResultBean.success("登陆成功"));
    }

    /**
     * 刷新token
     *
     * @param token
     * @param request
     * @param response
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("BLOG_TOKEN") String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从token中解析token信息
            UserInfo userInfo = JwtUtils.getUserInfo(this.jwtProperties.getPublicKey(), token);
            // 解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            // 更新cookie中的token
            CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), token);
            // 解析成功返回用户信息
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            log.error("非法用户登录！");
            e.printStackTrace();

        }
        // 出现异常则，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
