package org.tiger.framework.mq.consumers;

import static kafka.consumer.Consumer.createJavaConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.tiger.framework.common.cache.RedisService;
import org.tiger.framework.common.config.KafkaProperties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 消费者线程处理池
 * 
 * @author lihj17
 *         
 */
@Component
public class KafkaThreadPool
{
    
    @Resource
    private KafkaProperties kafkaProperties;
    
    @Resource
    private KafkaConfigFactory consumerConfigFactory;
    
    @Resource
    private RedisService<Object> redisService;
    
    private ConsumerConnector consumer;
    
    private ExecutorService threadPool;
    
    @PostConstruct
    public void startConsuming()
    {
        threadPool = Executors.newFixedThreadPool(kafkaProperties.getConsumerThreadNum());
        ConsumerConfig consumerConfig = consumerConfigFactory.getConsumerConfig(kafkaProperties);
        consumer = createJavaConsumerConnector(consumerConfig);
        consume();
    }
    
    /**
     * 消费数据
     */
    public void consume()
    {
        String topic = kafkaProperties.getTopic();
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, kafkaProperties.getConsumerThreadNum());
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
        
        for (final KafkaStream<byte[], byte[]> stream : streams)
        {
            threadPool.submit(new KafkaConsumer(stream));
        }
    }
}
