package com.blog.service;

import com.blog.client.UserClient;
import com.blog.entity.UserInfo;
import com.blog.enums.ExceptionEnum;
import com.blog.exception.LyException;
import com.blog.model.User;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtils;
import com.blog.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author bystander
 * @date 2018/10/1
 */
@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Resource
    private JwtProperties jwtProperties;


    public String authentication(UserVo userVo) {
        try {
            User user = userClient.queryUser(userVo.getUsername(),userVo.getPassword());
            if (user == null) {
                return null;
            }
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            //生成Token
            String token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            System.out.println("--------------------token"+token);
            return token;
        } catch (Exception e) {
            log.error("【授权中心】用户名和密码错误，用户名：{}", userVo.getUsername(), e);
            throw new LyException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}
