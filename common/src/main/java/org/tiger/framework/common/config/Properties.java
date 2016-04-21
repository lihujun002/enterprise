package org.tiger.framework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * kafka消费者配置
 * 
 * @author lihj17
 *        
 */
@Component(value = "properties")
@ConfigurationProperties(prefix = "kafka")
public class Properties
{
    /**
     * zookeeper连接地址
     */
    private String zookeeperConnect;
    
    private String groupId;
    
    private String serializerClass;
    
    private String zookeeperSessionTimeoutMs;
    
    private String zookeeperSyncTimeMs;
    
    private String autoCommitIntervalMs;
    
    private String topic;
    
    private Integer consumerThreadNum;
    
    public String getZookeeperConnect()
    {
        return zookeeperConnect;
    }
    
    public void setZookeeperConnect(String zookeeperConnect)
    {
        this.zookeeperConnect = zookeeperConnect;
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
    
    public String getZookeeperSessionTimeoutMs()
    {
        return zookeeperSessionTimeoutMs;
    }
    
    public void setZookeeperSessionTimeoutMs(String zookeeperSessionTimeoutMs)
    {
        this.zookeeperSessionTimeoutMs = zookeeperSessionTimeoutMs;
    }
    
    public String getZookeeperSyncTimeMs()
    {
        return zookeeperSyncTimeMs;
    }
    
    public void setZookeeperSyncTimeMs(String zookeeperSyncTimeMs)
    {
        this.zookeeperSyncTimeMs = zookeeperSyncTimeMs;
    }
    
    public String getAutoCommitIntervalMs()
    {
        return autoCommitIntervalMs;
    }
    
    public void setAutoCommitIntervalMs(String autoCommitIntervalMs)
    {
        this.autoCommitIntervalMs = autoCommitIntervalMs;
    }
    
    public String getTopic()
    {
        return topic;
    }
    
    public void setTopic(String topic)
    {
        this.topic = topic;
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
