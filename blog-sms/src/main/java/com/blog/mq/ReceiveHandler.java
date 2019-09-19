package com.blog.mq;

import com.blog.config.RabbitmqConfig;
import com.blog.util.SenCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReceiveHandler {
    @Value("${sms.host}")
    private String smsHost;
    @Value("${sms.path}")
    private String smsPath;
    @Value("${sms.appcode}")
    private String myAppcode;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(Map map) {
        String code = (String) map.get("code");
        String phone = (String) map.get("phone");
        if (StringUtils.isBlank(phone)) {
            throw new RuntimeException("电话号码不能为空!");
        } else if (StringUtils.isBlank(code)) {
            throw new RuntimeException("验证码不能为空!");
        } else {
            System.out.println("222-------------------------------"+code);
            SenCodeUtil.sendCode(smsHost, smsPath, myAppcode, phone, code);
        }
    }
}
