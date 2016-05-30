package org.tiger.framework.mq.consumers;

import java.util.Properties;

import org.springframework.stereotype.Component;
import org.tiger.framework.common.config.KafkaProperties;

/**
 * 消费者配置工厂
 * 
 * @author lihj17
 *
 */
@Component
public class KafkaConfigFactory {

	/**
	 * 获取消费者配置
	 * @param properties
	 * @return
	 */
	public Properties getConsumerConfig(KafkaProperties properties) {
	    Properties props = new Properties();
        props.put("bootstrap.servers", properties.getBootstrapServers());
        props.put("group.id", properties.getGroupId());
        props.put("enable.auto.commit", properties.getEnableAutoCommit());
        props.put("auto.commit.interval.ms", properties.getAutoCommitIntervalMs());
        props.put("session.timeout.ms", properties.getSessionTimeoutMs());
        props.put("key.deserializer", properties.getKeyDeserializer());
        props.put("value.deserializer", properties.getValueDeserializer());
        return props;
	}
}
