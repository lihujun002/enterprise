package org.tiger.framework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * kafka消费者配置
 * 
 * @author lihj17
 *         
 */
@Component(value = "rabbitMqProperties")
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqProperties
{
    /**
     * 队列名称
     */
    public String queueName;
    
    /**
     * 交换器名称
     */
    public String exchangeName;
    
    /**
     * 路由key
     */
    public String routingKey;
    
    /**
     * 是否持久化
     */
    public boolean durable;
    
    public String getQueueName()
    {
        return queueName;
    }
    
    public void setQueueName(String queueName)
    {
        this.queueName = queueName;
    }
    
    public String getExchangeName()
    {
        return exchangeName;
    }
    
    public void setExchangeName(String exchangeName)
    {
        this.exchangeName = exchangeName;
    }
    
    public String getRoutingKey()
    {
        return routingKey;
    }
    
    public void setRoutingKey(String routingKey)
    {
        this.routingKey = routingKey;
    }
    
    public boolean isDurable()
    {
        return durable;
    }
    
    public void setDurable(boolean durable)
    {
        this.durable = durable;
    }
    
}
