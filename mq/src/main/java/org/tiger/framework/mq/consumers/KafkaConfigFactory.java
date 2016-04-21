package org.tiger.framework.mq.consumers;

import java.util.Properties;

import org.springframework.stereotype.Component;
import org.tiger.framework.common.config.KafkaProperties;

import kafka.consumer.ConsumerConfig;

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
	public ConsumerConfig getConsumerConfig(KafkaProperties properties) {
		Properties props = new Properties();
		props.put("zookeeper.connect", properties.getZookeeperConnect());
		props.put("group.id", properties.getGroupId());
		props.put("serializer.class", properties.getSerializerClass());
		props.put("zookeeper.session.timeout.ms", properties.getZookeeperSessionTimeoutMs());
		props.put("zookeeper.sync.time.ms", properties.getZookeeperSyncTimeMs());
		props.put("auto.commit.interval.ms", properties.getAutoCommitIntervalMs());
		return new ConsumerConfig(props);
	}
}
