package org.tiger.framework.mq.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka消息消费者
 * 
 * @author lihj17
 *         
 */
public class KafkaConsumerThread implements Runnable
{
    
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerThread.class);
    
    ConsumerRecord<String, byte[]> consumerRecord;
    
    public KafkaConsumerThread(ConsumerRecord<String, byte[]> consumerRecord)
    {
        this.consumerRecord = consumerRecord;
    }
    
    @Override
    public void run()
    {
        byte[] messageData = consumerRecord.value();
        
        try
        {
            String message = new String(messageData, "UTF-8");
            logger.info("revice kafka message:" + message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("revice kafka message error" + e.getMessage());
        }
        logger.info("Shutting down Thread: " + messageData);
    }
    
}
