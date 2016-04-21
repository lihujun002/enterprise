package org.tiger.framework;

import java.util.concurrent.Executor;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.tiger.framework.common.config.RabbitMqProperties;
import org.tiger.framework.mq.consumers.RabbitMqConsumer;

@SpringBootApplication
@EnableConfigurationProperties({RabbitMqProperties.class})
public class MqApplication {
    
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    @Autowired
    RabbitMqProperties rabbitMqProperties;
    
    /**
     * 定义队列
     * 
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(rabbitMqProperties.getQueueName(), true);// 是否持久化
    }
    
    /**
     * 定义数据交换器
     * 
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(rabbitMqProperties.getExchangeName(), false, false);// 电商此处设置的是false false
    }
    
    /**
     * 绑定队列 订阅topic消息 properties.rabbitmqQueueName
     * 
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(rabbitMqProperties.getRoutingKey());
    }
    
    
    /**
     * 配置线程池
     * @return
     */
    @Bean
    Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);//线程池维护线程的最少数量  
        taskExecutor.setKeepAliveSeconds(30000);//线程池维护线程所允许的空闲时间  
        taskExecutor.setMaxPoolSize(10);//线程池维护线程的最大数量 
        taskExecutor.setQueueCapacity(50);//线程池所使用的缓冲队列  
        return taskExecutor;
    }
    
    /**
     * 初始化配置
     * 
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter,Executor taskExecutor) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);// 设置连接
        container.setQueueNames(rabbitMqProperties.getQueueName());// 设置队列名称
        container.setMessageListener(listenerAdapter);// 设置监听
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);// 设置为自动回复 ack
        container.setTaskExecutor(taskExecutor);//配置线程池
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);//最大并发用户

        return container;
    }
    
    

    
    /**
     * 定义接收器
     * 
     * @return
     */
    @Bean
    RabbitMqConsumer rabbitMqConsumer() {
        return new RabbitMqConsumer();
    }
    
    /**
     * 指定接收监听
     * 
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMqConsumer rabbitMqConsumer) {
        // 指定消息接收Adapter类为 receiver 方法名为 receiveMessage
        return new MessageListenerAdapter(rabbitMqConsumer, "receiveMessage");
    }
    
    /**
     * 启动
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MqApplication.class, args);
    }
}
