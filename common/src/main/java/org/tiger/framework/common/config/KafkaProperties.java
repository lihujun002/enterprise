package org.tiger.framework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * kafka消费者配置
 * 
 * @author lihj17
 *        
 */
@Component(value = "kafkaProperties")
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties
{
    /**
     * kafka连接地址
     */
    private String bootstrapServers;
    
    private String groupId;
    
    private String serializerClass;
    
    private String enableAutoCommit;
    
    private String sessionTimeoutMs;
    
    private String autoCommitIntervalMs;
    
    private String keyDeserializer;
    
    private String valueDeserializer;
    
    private String topics;
    
    private Integer consumerThreadNum;
    
    public String getBootstrapServers()
    {
        return bootstrapServers;
    }
    
    public void setBootstrapServers(String bootstrapServers)
    {
        this.bootstrapServers = bootstrapServers;
    }
    
    public String getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
    
    public String getSerializerClass()
    {
        return serializerClass;
    }
    
    public void setSerializerClass(String serializerClass)
    {
        this.serializerClass = serializerClass;
    }
    
    public String getEnableAutoCommit()
    {
        return enableAutoCommit;
    }
    
    public void setEnableAutoCommit(String enableAutoCommit)
    {
        this.enableAutoCommit = enableAutoCommit;
    }
    
    public String getSessionTimeoutMs()
    {
        return sessionTimeoutMs;
    }
    
    public void setSessionTimeoutMs(String sessionTimeoutMs)
    {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }
    
    public String getAutoCommitIntervalMs()
    {
        return autoCommitIntervalMs;
    }
    
    public void setAutoCommitIntervalMs(String autoCommitIntervalMs)
    {
        this.autoCommitIntervalMs = autoCommitIntervalMs;
    }
    
    public String getKeyDeserializer()
    {
        return keyDeserializer;
    }
    
    public void setKeyDeserializer(String keyDeserializer)
    {
        this.keyDeserializer = keyDeserializer;
    }
    
    public String getValueDeserializer()
    {
        return valueDeserializer;
    }
    
    public void setValueDeserializer(String valueDeserializer)
    {
        this.valueDeserializer = valueDeserializer;
    }
    
    public String getTopics()
    {
        return topics;
    }
    
    public void setTopics(String topics)
    {
        this.topics = topics;
    }
    
    public Integer getConsumerThreadNum()
    {
        return consumerThreadNum;
    }
    
    public void setConsumerThreadNum(Integer consumerThreadNum)
    {
        this.consumerThreadNum = consumerThreadNum;
    }
    
}
