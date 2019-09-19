package com.blog.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.config
 * @Author: ly
 * @CreateTime: 2019-08-27 10:50
 * @Description: rabbitmq短信验证消息队列配置
 */
@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_DIRECT_INFORM = "exchange_direct_inform";
    public static final String routingKey = "sms";

    /**
     * 声明sms消息队列
     *
     * @return
     */
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        Queue queue = new Queue(QUEUE_INFORM_SMS);
        return queue;
    }
    /**
     * 声明sms交换机
     *
     * @return
     */
    @Bean(EXCHANGE_DIRECT_INFORM)
    public Exchange EXCHANGE_DIRECT_INFORM() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_DIRECT_INFORM).durable(true).build();
    }

    /**
     * 将消息队列和交换机绑定
     * @param smsQue
     * @param smsExchange
     * @return
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue smsQue, @Qualifier(EXCHANGE_DIRECT_INFORM) Exchange smsExchange) {
        return BindingBuilder.bind(smsQue).to(smsExchange).with(routingKey).noargs();
    }

}
