package org.tiger.framework.mq.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rabbitmq 消费
 * 
 * @author lihj17
 *         
 */
public class RabbitMqConsumer
{
    
    private final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);
    
    /**
     * 接收消息
     * 
     * @param message
     */
    public void receiveMessage(String msg)
    {
        logger.info(" receiveMessage:" + msg);
    }
}
