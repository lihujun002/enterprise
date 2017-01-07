package org.tiger.framework.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @author lihj17
 *        
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
    
    @Autowired
    private StompSocketHandshakeInterceptor webSocketHandshakeInterceptor;
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        // 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
        registry.addEndpoint("/stompwechat")
            .setAllowedOrigins("*")
            .addInterceptors(webSocketHandshakeInterceptor)
            .withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        // 这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
        // 在topic这个域上可以向客户端发消息,也就是服务器可以发送的地址
        registry.enableSimpleBroker("/topic");
        //这里设置的app的目的地 设置了之后客户端发过来的请求前缀必须是 /app
//        registry.setApplicationDestinationPrefixes("/app");
    }
    
}
