package com.blog.service;

import com.blog.Constatnt.UserConstant;
import com.blog.UserService;
import com.blog.config.RabbitmqConfig;
import com.blog.enums.ExceptionEnum;
import com.blog.exception.LyException;
import com.blog.mapper.UserDetailMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.model.UserDetail;
import com.blog.utils.*;
import com.blog.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailMapper userDetailMapper;
    @Resource
    RedisUtils redisUtils;


    /**
     * 用户注册
     *
     * @param userVo
     */
    @Override
    public void register(@Valid UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setId(null);
        UserDetail userDetail = new UserDetail();
        String key = UserConstant.KEY_PREFIX.value() + user.getPhone();
        String value = (String) redisUtils.get(key);
        if (!StringUtils.equals(userVo.getCode(), value)) {
            //验证码不匹配
            log.error("验证码不匹配!");
            throw new LyException(ExceptionEnum.VERIFY_CODE_NOT_MATCHING);
        }
        User queryUser = queryUser(user.getUsername(), user.getPassword());
        if(queryUser!=null){
            log.error("该用户已存在!");
            throw new LyException(ExceptionEnum.EXIST_USER);
        }

        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setStatus(UserConstant.Normal_Status.value());//默认用户状态为正常状态
        //生成密码
        String md5Pwd = CodecUtils.md5Hex(user.getPassword(), user.getSalt());
        user.setPassword(md5Pwd);
        user.setCreatetime(DateUtils.getSysTimestamp());
        user.setUpdatetime(DateUtils.getSysTimestamp());
        //保存User到数据库
        int userCount = userMapper.insert(user);
        userDetail.setUserId(user.getId());
        int userDetaiCount = userDetailMapper.insert(userDetail);
        if (userCount != 1 && userDetaiCount != 1) {
            throw new LyException(ExceptionEnum.INVALID_PARAM);
        }
        //把验证码从Redis中删除
        try {
            redisUtils.del(key);
        } catch (Exception e) {
            log.error("删除缓存验证码失败，code：{}", userVo.getCode(), e);
        }
    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    @Override
    public void sendVerifyCode(String phone) {
        boolean chinaPhoneLegal = PhoneFormatCheckUtils.isChinaPhoneLegal(phone);
        if (!chinaPhoneLegal) {
            throw new LyException(ExceptionEnum.PHONE_FORMAT_ERROR);
        }
        //随机生成6位数字验证码
        String code = NumberUtils.generateCode(6);

        String key = UserConstant.KEY_PREFIX.value() + phone;

        //把验证码放入Redis中，
        redisUtils.set(key, code, 300);

        //向mq中发送消息
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_DIRECT_INFORM, RabbitmqConfig.routingKey, map);
    }

    /**
     * 查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        // 校验用户名
        if (user == null) {
            log.warn("没有该用户");
            return null;
        }
        // 校验密码
        if (!user.getPassword().equals(CodecUtils.md5Hex(password, user.getSalt()))) {
            return null;
        }
        // 用户名密码都正确
        return user;
    }
}
