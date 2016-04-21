package org.tiger.framework.mq.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * kafka消息消费者
 * 
 * @author lihj17
 *         
 */
public class KafkaConsumer implements Runnable
{
    
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    private KafkaStream<byte[], byte[]> kafkaStream;
    
    public KafkaConsumer(KafkaStream<byte[], byte[]> kafkaStream)
    {
        this.kafkaStream = kafkaStream;
    }
    
    @Override
    public void run()
    {
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
        
        while (it.hasNext())
        {
            byte[] messageData = it.next().message();
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
        }
        logger.info("Shutting down Thread: " + kafkaStream);
    }
    
}
