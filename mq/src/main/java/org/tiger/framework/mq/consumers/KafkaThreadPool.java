package org.tiger.framework.mq.consumers;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tiger.framework.common.cache.RedisService;
import org.tiger.framework.common.config.KafkaProperties;

/**
 * 消费者线程处理池
 * 
 * @author lihj17
 *         
 */
@Component
public class KafkaThreadPool
{
    
    private static Logger logger = LoggerFactory.getLogger(KafkaThreadPool.class);
    
    @Resource
    private KafkaProperties kafkaProperties;
    
    @Resource
    private KafkaConfigFactory consumerConfigFactory;
    
    @Resource
    private RedisService<Object> redisService;
    
    private KafkaConsumer<String, byte[]> consumer;
    
    private ExecutorService threadPool;
    
    @PostConstruct
    public void startConsuming()
    {
        threadPool = Executors.newFixedThreadPool(kafkaProperties.getConsumerThreadNum());
        consumer = new KafkaConsumer<String, byte[]>(consumerConfigFactory.getConsumerConfig(kafkaProperties));
        consumer.subscribe(Arrays.asList(kafkaProperties.getTopics()));
        new Thread(new ConsumeThread()).start();
    }
    
    /**
     * 消费者
     * 
     * @author lihj17
     *         
     */
    class ConsumeThread implements Runnable
    {
        @Override
        public void run()
        {
            logger.info("ConsumeThread run ......");
            while (true)
            {
                ConsumerRecords<String, byte[]> poll = consumer.poll(100);
                for (ConsumerRecord<String, byte[]> consumerRecord : poll)
                {
                    threadPool.submit(new KafkaConsumerThread(consumerRecord));
                }
            }
        }
        
    }
}
