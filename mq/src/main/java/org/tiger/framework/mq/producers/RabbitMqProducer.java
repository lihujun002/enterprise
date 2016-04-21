package org.tiger.framework.mq.producers;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * rabbitMq生产者
 * @author lihj17
 *
 */
@Component
public class RabbitMqProducer
{
    /**
     * 
     */
    @Resource
    private RabbitTemplate rabbitTemplate;
    
    /**
     * 发送消息
     * @param routingKey 路由key
     * @param message    消息
     */
    public void send(String routingKey, String message)
    {
        this.rabbitTemplate.convertAndSend(routingKey, message);
    }
    
    /**
     * 发送消息
     * @param exchangeName 交换器名称
     * @param routingKey   路由key
     * @param message      消息
     */
    public void send(String exchangeName, String routingKey, String message)
    {
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
    
}
